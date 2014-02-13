// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import android.util.Log;
import java.io.*;
import java.net.*;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownloadService
{
    public static interface DownloadProcessCallBack
    {

        public abstract void updateProcess(int i, long l);
    }


    public DownloadService()
    {
        isCancelDownload = false;
    }

    public boolean downloadFile(String s, String s1, String s2, DownloadProcessCallBack downloadprocesscallback)
        throws ClientProtocolException, IOException
    {
//    	 return true;
        boolean flag = false;
//_L2:
//        return flag;
        isCancelDownload = false;
        DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
        File file = new File(s1);
        if(!file.isDirectory() || !file.exists())
            file.mkdir();
        HttpGet httpget = new HttpGet(s);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19");
        HttpResponse httpresponse = defaulthttpclient.execute(httpget);
        HttpEntity httpentity;
        long l;
        if(200 != httpresponse.getStatusLine().getStatusCode())
        {
//label0:
//            {
                if(302 != httpresponse.getStatusLine().getStatusCode()) {
                	return false;
                }
//                    break label0;
                String s3 = httpresponse.getHeaders("location")[0].getValue();
                System.out.println(s3);
                httpget = new HttpGet(s3);
                httpget.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
                httpresponse = defaulthttpclient.execute(httpget);
//            }
        }
        httpentity = httpresponse.getEntity();
        l = httpentity.getContentLength();
        if(l <= 0L)
            System.out.println("\u65E0\u6CD5\u83B7\u77E5\u6587\u4EF6\u5927\u5C0F ");
        System.out.println((new StringBuilder()).append("input.available() ").append(l).toString());
        if(httpentity != null)
        {
            System.out.println(httpentity.getContentType());
            if(httpentity.isStreaming())
            {
                File file1 = new File((new StringBuilder()).append(s1).append(s2).append(".dl").toString());
                if(file1.exists())
                    file1.delete();
                FileOutputStream fileoutputstream = new FileOutputStream(file1);
                InputStream inputstream = httpentity.getContent();
                long l1 = l / 100L;
                int i = 1;
                long l2 = 0L;
                byte abyte0[] = new byte[1024];
//                boolean flag;
                do
                {
                    int j = inputstream.read(abyte0);
                    if(j == -1 || isCancelDownload)
                        break;
                    fileoutputstream.write(abyte0, 0, j);
                    l2 += j;
                    if(l2 > l1 * (long)i && downloadprocesscallback != null)
                    {
                        int k = i + 1;
                        downloadprocesscallback.updateProcess(i, l2);
                        i = k;
                    }
                } while(true);
                fileoutputstream.flush();
                fileoutputstream.close();
                if(!isCancelDownload)
                {
                    file1.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
                    Log.i("download complete", file1.getAbsolutePath());
                } else
                {
                    file1.delete();
                    Log.i("download cancel", file1.getAbsolutePath());
                }
            }
        }
        httpget.abort();
        flag = true;
        
        return true;
//        if(true) goto _L2; else goto _L1
//_L1:
    }

    public void downloadFileByByte(String s, String s1, String s2)
    {
    	try{
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        httpurlconnection.setRequestMethod("GET");
        httpurlconnection.setConnectTimeout(6000);
        System.out.println(httpurlconnection.getResponseCode());
        if(httpurlconnection.getResponseCode() == 200)
        {
            byte abyte0[] = readStream(httpurlconnection.getInputStream());
            FileOutputStream fileoutputstream = new FileOutputStream(new File((new StringBuilder()).append(s1).append(s2).toString()));
            fileoutputstream.write(abyte0);
            fileoutputstream.close();
        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
//_L1:
//        return;
//        MalformedURLException malformedurlexception;
//        malformedurlexception;
//        malformedurlexception.printStackTrace();
//          goto _L1
//        ProtocolException protocolexception;
//        protocolexception;
//        protocolexception.printStackTrace();
//          goto _L1
//        FileNotFoundException filenotfoundexception;
//        filenotfoundexception;
//        filenotfoundexception.printStackTrace();
//          goto _L1
//        IOException ioexception;
//        ioexception;
//        ioexception.printStackTrace();
//          goto _L1
//        Exception exception;
//        exception;
//        exception.printStackTrace();
//          goto _L1
    }

    public void downloadFileByRandomAccess(String s, String s1, String s2, DownloadProcessCallBack downloadprocesscallback)
    {
        InputStream inputstream= null;
        RandomAccessFile randomaccessfile = null;
        File file;
        isCancelDownload = false;
        inputstream = null;
        randomaccessfile = null;
        file = new File((new StringBuilder()).append(s1).append(s2).append(".dl").toString());
        HttpURLConnection httpurlconnection;
        RandomAccessFile randomaccessfile1 = null;
        InputStream inputstream1 = null;
        try {
			

        httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        httpurlconnection.setConnectTimeout(5000);
        httpurlconnection.setRequestMethod("GET");
        httpurlconnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19");
        httpurlconnection.setRequestProperty("accept", "*/*");
//        if(httpurlconnection.getResponseCode() != 200) goto _L2; else goto _L1
//_L1:
        if(httpurlconnection.getResponseCode()  == 200) { //lieb add
        
        int i;
        i = httpurlconnection.getContentLength();
        randomaccessfile1 = new RandomAccessFile(file, "rw");
        long l = i;
        InputStream inputstream2;
        randomaccessfile1.setLength(l);
        inputstream2 = httpurlconnection.getInputStream();
        inputstream1 = inputstream2;
        byte abyte0[] = new byte[1024];
        int j = 0;
        int k = i / 100;
        long l1 = 0L;
        do
        {
            int i1 = inputstream1.read(abyte0);
            if(i1 == -1 || isCancelDownload)
                break;
            randomaccessfile1.write(abyte0, 0, i1);
            if(downloadprocesscallback != null)
            {
                l1 += i1;
                if(l1 >= (long)(k * j))
                {
                    j++;
                    downloadprocesscallback.updateProcess(j, l1);
                }
            }
        } while(true);
        
        }// lie add 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			if(randomaccessfile1 != null) {
				try {
					randomaccessfile1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(inputstream1 != null) {
				try {
					inputstream1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        
      if(isCancelDownload)
      {
          file.delete();
          Log.i("download cancel", "file delete");
      } else
      {
          file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
          Log.i("download complete", file.getAbsolutePath());
      }
        
        
//          goto _L3
//        MalformedURLException malformedurlexception;
//        malformedurlexception;
//_L11:
//        malformedurlexception.printStackTrace();
//        IOException ioexception16;
//        IOException ioexception17;
//        IOException ioexception18;
//        if(randomaccessfile1 != null)
//            try
//            {
//                randomaccessfile1.close();
//            }
//            catch(IOException ioexception5)
//            {
//                ioexception5.printStackTrace();
//            }
//        if(inputstream1 != null)
//            try
//            {
//                inputstream1.close();
//            }
//            catch(IOException ioexception4)
//            {
//                ioexception4.printStackTrace();
//            }
//        if(randomaccessfile1 != null)
//            try
//            {
//                randomaccessfile1.close();
//            }
//            catch(IOException ioexception3)
//            {
//                ioexception3.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//_L4:
//        return;
//_L2:
//        randomaccessfile1 = null;
//        inputstream1 = null;
//_L3:
//        if(randomaccessfile1 != null)
//            try
//            {
//                randomaccessfile1.close();
//            }
//            // Misplaced declaration of an exception variable
//            catch(IOException ioexception18)
//            {
//                ioexception18.printStackTrace();
//            }
//        if(inputstream1 != null)
//            try
//            {
//                inputstream1.close();
//            }
//            // Misplaced declaration of an exception variable
//            catch(IOException ioexception17)
//            {
//                ioexception17.printStackTrace();
//            }
//        if(randomaccessfile1 != null)
//            try
//            {
//                randomaccessfile1.close();
//            }
//            // Misplaced declaration of an exception variable
//            catch(IOException ioexception16)
//            {
//                ioexception16.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//        break MISSING_BLOCK_LABEL_300;
//        ProtocolException protocolexception;
//        protocolexception;
//_L9:
//        protocolexception.printStackTrace();
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception15)
//            {
//                ioexception15.printStackTrace();
//            }
//        if(inputstream != null)
//            try
//            {
//                inputstream.close();
//            }
//            catch(IOException ioexception14)
//            {
//                ioexception14.printStackTrace();
//            }
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception13)
//            {
//                ioexception13.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//          goto _L4
//        FileNotFoundException filenotfoundexception;
//        filenotfoundexception;
//_L8:
//        filenotfoundexception.printStackTrace();
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception12)
//            {
//                ioexception12.printStackTrace();
//            }
//        if(inputstream != null)
//            try
//            {
//                inputstream.close();
//            }
//            catch(IOException ioexception11)
//            {
//                ioexception11.printStackTrace();
//            }
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception10)
//            {
//                ioexception10.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//          goto _L4
//        IOException ioexception6;
//        ioexception6;
//_L7:
//        ioexception6.printStackTrace();
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception9)
//            {
//                ioexception9.printStackTrace();
//            }
//        if(inputstream != null)
//            try
//            {
//                inputstream.close();
//            }
//            catch(IOException ioexception8)
//            {
//                ioexception8.printStackTrace();
//            }
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception7)
//            {
//                ioexception7.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//          goto _L4
//        Exception exception;
//        exception;
//_L6:
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception2)
//            {
//                ioexception2.printStackTrace();
//            }
//        if(inputstream != null)
//            try
//            {
//                inputstream.close();
//            }
//            catch(IOException ioexception1)
//            {
//                ioexception1.printStackTrace();
//            }
//        if(randomaccessfile != null)
//            try
//            {
//                randomaccessfile.close();
//            }
//            catch(IOException ioexception)
//            {
//                ioexception.printStackTrace();
//            }
//        if(isCancelDownload)
//        {
//            file.delete();
//            Log.i("download cancel", "file delete");
//        } else
//        {
//            file.renameTo(new File((new StringBuilder()).append(s1).append(s2).toString()));
//            Log.i("download complete", file.getAbsolutePath());
//        }
//        throw exception;
//        exception;
//        randomaccessfile = randomaccessfile1;
//        continue; /* Loop/switch isn't completed */
//        exception;
//        inputstream = inputstream1;
//        randomaccessfile = randomaccessfile1;
//        if(true) goto _L6; else goto _L5
//_L5:
//        ioexception6;
//        randomaccessfile = randomaccessfile1;
//          goto _L7
//        ioexception6;
//        inputstream = inputstream1;
//        randomaccessfile = randomaccessfile1;
//          goto _L7
//        filenotfoundexception;
//        randomaccessfile = randomaccessfile1;
//          goto _L8
//        filenotfoundexception;
//        inputstream = inputstream1;
//        randomaccessfile = randomaccessfile1;
//          goto _L8
//        protocolexception;
//        randomaccessfile = randomaccessfile1;
//          goto _L9
//        protocolexception;
//        inputstream = inputstream1;
//        randomaccessfile = randomaccessfile1;
//          goto _L9
//        malformedurlexception;
//        randomaccessfile1 = null;
//        inputstream1 = null;
//        continue; /* Loop/switch isn't completed */
//        malformedurlexception;
//        inputstream1 = null;
//        if(true) goto _L11; else goto _L10
//_L10:
    }

    public byte[] readStream(InputStream inputstream)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i != -1)
            {
                bytearrayoutputstream.write(abyte0, 0, i);
            } else
            {
                bytearrayoutputstream.close();
                inputstream.close();
                return bytearrayoutputstream.toByteArray();
            }
        } while(true);
    }

    public boolean isCancelDownload;
}
