// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package org.vv.business:
//            SDCardHelper

public class CacheService
{

    public CacheService()
    {
    }

    public static List read(String s, String as[])
    {
        ArrayList arraylist = new ArrayList();
        if(!SDCardHelper.hasSDCard()) {//goto _L2; else goto _L1
        	return arraylist;
        }
        		
//_L1:
        File file = new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(s).toString());
        if(!file.exists()) {//goto _L2; else goto _L3
        	return arraylist;
        }
//_L3:
        int j;
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        NodeList nodelist;
        int i;
        Element element;
        int k;
        int l;
        String s1;
        Document document1= null;
        try
        {
            document1 = documentbuilderfactory.newDocumentBuilder().parse(file);
        }
        catch(ParserConfigurationException parserconfigurationexception)
        {
            parserconfigurationexception.printStackTrace();
//            continue; /* Loop/switch isn't completed */
        }
        catch(SAXException saxexception)
        {
            saxexception.printStackTrace();
//            continue; /* Loop/switch isn't completed */
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
//            continue; /* Loop/switch isn't completed */
        }
        document = document1;
//_L5:
        nodelist = document.getDocumentElement().getElementsByTagName("node");
        i = nodelist.getLength();
        j = 0;
        do
        {
            if(j >= i)
                break;
            element = (Element)nodelist.item(j);
            HashMap hashmap = new HashMap();
            k = as.length;
            for(l = 0; l < k; l++)
            {
                s1 = as[l];
                hashmap.put(s1, element.getElementsByTagName(s1).item(0).getTextContent());
            }

            arraylist.add(hashmap);
            j++;
        } while(true);
//_L2:
        return arraylist;
//        if(true) goto _L5; else goto _L4
//_L4:
    }

    public static String readTxt(File file)
    {
//    	return null;
    	
        StringBuffer stringbuffer;
        BufferedReader bufferedreader;
        stringbuffer = new StringBuffer();
        bufferedreader = null;
        BufferedReader bufferedreader1 =null;
        try {
			bufferedreader1 = new BufferedReader(new FileReader(file));

//_L4:
        String s = bufferedreader1.readLine();
        while(s != null) {//goto _L2; else goto _L1
        	stringbuffer.append(s);
        	s = bufferedreader1.readLine();
        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(null != bufferedreader1) {
				try {
					bufferedreader1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
        return stringbuffer.toString();
//_L1:
//        stringbuffer.append(s);
//        if(true) goto _L4; else goto _L3
//_L3:
//        FileNotFoundException filenotfoundexception;
//        filenotfoundexception;
//_L10:
//        filenotfoundexception.printStackTrace();
//        if(bufferedreader1 != null)
//            try
//            {
//                bufferedreader1.close();
//            }
//            catch(IOException ioexception3)
//            {
//                ioexception3.printStackTrace();
//            }
//_L5:
//        return stringbuffer.toString();
//_L2:
//        if(bufferedreader1 != null)
//            try
//            {
//                bufferedreader1.close();
//            }
//            catch(IOException ioexception4)
//            {
//                ioexception4.printStackTrace();
//            }
//        break MISSING_BLOCK_LABEL_62;
//        IOException ioexception;
//        ioexception;
//_L8:
//        ioexception.printStackTrace();
//        if(bufferedreader != null)
//            try
//            {
//                bufferedreader.close();
//            }
//            catch(IOException ioexception2)
//            {
//                ioexception2.printStackTrace();
//            }
//          goto _L5
//        Exception exception;
//        exception;
//_L7:
//        if(bufferedreader != null)
//            try
//            {
//                bufferedreader.close();
//            }
//            catch(IOException ioexception1)
//            {
//                ioexception1.printStackTrace();
//            }
//        throw exception;
//        exception;
//        bufferedreader = bufferedreader1;
//        if(true) goto _L7; else goto _L6
//_L6:
//        ioexception;
//        bufferedreader = bufferedreader1;
//          goto _L8
//        filenotfoundexception;
//        bufferedreader1 = null;
//        if(true) goto _L10; else goto _L9
//_L9:
    }

    public static void write(List list, String s)
    {
        File file;
        DocumentBuilderFactory documentbuilderfactory;
        DocumentBuilder documentbuilder;
        if(!SDCardHelper.hasSDCard()) {
//            break MISSING_BLOCK_LABEL_308;
        	return;
        }
        file = new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(s).toString());
        if(file.exists())
            file.delete();
        documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilder = null;
        DocumentBuilder documentbuilder1;
		try {
			documentbuilder1 = documentbuilderfactory.newDocumentBuilder();
	
        documentbuilder = documentbuilder1;
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//_L1:
        Document document;
        Element element;
        Iterator iterator;
        document = documentbuilder.newDocument();
        element = document.createElement("root");
        iterator = list.iterator();
//_L2:
        Element element1;
//        if(!iterator.hasNext())
//            break MISSING_BLOCK_LABEL_246;
        while(iterator.hasNext()) {
        Map map = (Map)iterator.next();
        element1 = document.createElement("node");
        Element element2;
        for(Iterator iterator1 = map.entrySet().iterator(); iterator1.hasNext(); element1.appendChild(element2))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            String s1 = (String)entry.getKey();
            String s2 = (String)entry.getValue();
            element2 = document.createElement(s1);
            element2.appendChild(document.createCDATASection(s2));
        }

//        break MISSING_BLOCK_LABEL_233;
//        ParserConfigurationException parserconfigurationexception;
//        parserconfigurationexception;
//        parserconfigurationexception.printStackTrace();
//          goto _L1
        element.appendChild(element1);
    }
//          goto _L2
        TransformerFactory transformerfactory;
        document.appendChild(element);
        transformerfactory = TransformerFactory.newInstance();
        try{
        Transformer transformer = transformerfactory.newTransformer();
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("indent", "yes");
        transformer.transform(new DOMSource(document), new StreamResult(file));
        }catch(Exception e) {
        	e.printStackTrace();
        }
//_L3:
//        return;
//        TransformerConfigurationException transformerconfigurationexception;
//        transformerconfigurationexception;
//        transformerconfigurationexception.printStackTrace();
//          goto _L3
//        IllegalArgumentException illegalargumentexception;
//        illegalargumentexception;
//        illegalargumentexception.printStackTrace();
//          goto _L3
//        TransformerException transformerexception;
//        transformerexception;
//        transformerexception.printStackTrace();
//          goto _L3
    }

    public static void writeTxt(File file, String s)
    {
        BufferedWriter bufferedwriter = null;
        try {
			

        bufferedwriter = new BufferedWriter(new FileWriter(file));
        bufferedwriter.write(s);
        bufferedwriter.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(null != bufferedwriter) {
				try {
					bufferedwriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

//            break MISSING_BLOCK_LABEL_35;

//        bufferedwriter1.close();
//_L1:
//        return;
//        IOException ioexception3;
//        ioexception3;
//        ioexception3.printStackTrace();
//          goto _L1
//        IOException ioexception;
//        ioexception;
//        bufferedwriter1 = null;
//_L4:
//        ioexception.printStackTrace();
//        if(bufferedwriter1 != null)
//            try
//            {
//                bufferedwriter1.close();
//            }
//            catch(IOException ioexception2)
//            {
//                ioexception2.printStackTrace();
//            }
//          goto _L1
//        Exception exception;
//        exception;
//_L3:
//        if(bufferedwriter != null)
//            try
//            {
//                bufferedwriter.close();
//            }
//            catch(IOException ioexception1)
//            {
//                ioexception1.printStackTrace();
//            }
//        throw exception;
//        exception;
//        bufferedwriter = bufferedwriter1;
//        if(true) goto _L3; else goto _L2
//_L2:
//        ioexception;
//          goto _L4
    }
}
