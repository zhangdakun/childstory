// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.children.story2;

import java.io.*;
import java.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Fetch
{

    public Fetch()
    {
    }

    public static List fetchCatelog(String s)
    {
        ArrayList arraylist;
        System.out.println(s);
        arraylist = new ArrayList();
        Document document1;
		try {
			document1 = Jsoup.connect(s).userAgent("Mozilla").timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			document1 = null;
		}
        Document document = document1;
//_L1:
//        IOException ioexception;
        ArrayList arraylist1;
        if(document == null || document.getElementsByClass("scccm") == null)
        {
            arraylist1 = arraylist;
        } else
        {
            Elements elements = document.getElementsByClass("scccm").first().getElementsByClass("sslist");
            System.out.println(elements);
            HashMap hashmap;
            for(Iterator iterator = elements.iterator(); iterator.hasNext(); arraylist.add(hashmap))
            {
                Element element = (Element)iterator.next();
                Element element1 = element.getElementsByClass("ssl2").first().getElementsByTag("a").first();
                String s1 = element1.text();
                String s2 = (new StringBuilder()).append("http://story.beva.com").append(element1.attr("href")).toString();
                String s3 = element.select("div>p").first().text();
                hashmap = new HashMap();
                hashmap.put("name", s1);
                hashmap.put("tip", s3);
                hashmap.put("nextUrl", s2);
            }

            arraylist1 = arraylist;
        }
        return arraylist1;
//        ioexception;
//        ioexception.printStackTrace();
//        document = null;
//          goto _L1
    }

    public static String fetchContent(String s)
    {
        Element element;
        StringBuffer stringbuffer;
        Element element2;
        Document document = null;
        Elements elements;
        Iterator iterator1;
        Document document1;
        Element element1;
        String s2;
        Iterator iterator2;
        Document document2;
        Document document3;
        try
        {
            document3 = Jsoup.connect(s).userAgent("Mozilla").timeout(10000).get();
            document = document3;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
//            continue; /* Loop/switch isn't completed */
        }
      
        if(null == document) {
        	return null; // lieb add
        }
//_L11:
        String s1 = null;
        element = document.getElementById("stcontent");
        if(element != null) {// goto _L2; else goto _L1
            element.select("p[class=stcatvc]").first().remove();
            for(Iterator iterator = element.select("div").iterator(); iterator.hasNext(); ((Element)iterator.next()).remove());
            s1 = element.toString();
        } else {
//_L1:
        elements = document.getElementById("slc").getElementsByClass("stch");
        stringbuffer = new StringBuffer();
        iterator1 = elements.iterator();
        document1 = document;
//_L7:
//        if(!iterator1.hasNext()) goto _L4; else goto _L3
		while(iterator1.hasNext()) {
//_L3:
        element1 = (Element)iterator1.next();
        s2 = (new StringBuilder()).append("http://story.beva.com").append(element1.getElementsByTag("a").first().attr("href")).toString();
        try {
			document2 = Jsoup.connect(s2).userAgent("Mozilla").timeout(10000).get();
			document1 = document2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//_L6:
        element2 = document1.getElementById("stcontent");
        element2.select("p[class=stcatvc]").first().remove();
        for(iterator2 = element2.select("div").iterator(); iterator2.hasNext(); ((Element)iterator2.next()).remove());
//        continue; /* Loop/switch isn't completed */
//        IOException ioexception1;
//        ioexception1;
//        ioexception1.printStackTrace();
//        if(true) goto _L6; else goto _L5
//_L5:
        stringbuffer.append(element2.toString());
		}
//          goto _L7
//_L4:
        s1 = stringbuffer.toString();
        }
//_L9:
        return s1;
//_L2:
//        element.select("p[class=stcatvc]").first().remove();
//        for(Iterator iterator = element.select("div").iterator(); iterator.hasNext(); ((Element)iterator.next()).remove());
//        s1 = element.toString();
//        if(true) goto _L9; else goto _L8
//_L8:
//        if(true) goto _L11; else goto _L10
//_L10:
        

    }

    public static String getMP3URL(String s) throws IOException
    {
        Document document = null;
        Document document1;
		try {
			document1 = Jsoup.connect(s).userAgent("Mozilla").timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			document1 = null;
		}
        document = document1;
        
        if(null == document) {
        	throw new IOException("null exception");
//        	return null;
        }
//_L1:
        BufferedReader bufferedreader;
        String s1;
        bufferedreader = new BufferedReader(new StringReader(document.getElementById("ksplayer").toString()));
        s1 = "";
        String s2;
        try{
        do
        {
            s1 = bufferedreader.readLine();
            if(s1 == null)
//                break MISSING_BLOCK_LABEL_192;
            	break;
        } while(s1.indexOf("fileUrl:") == -1);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        s2 = s1.split(":", 2)[1];
//_L3:
//        IOException ioexception;
        String s3;
//        try
//        {
//            bufferedreader.close();
//        }
//        catch(IOException ioexception4)
//        {
//            ioexception4.printStackTrace();
//        }
//_L2:
        s3 = s2.replaceAll("\\'", "").replaceAll(",", "");
//        System.out.println(s3);
//        return s3;
//        ioexception;
//        ioexception.printStackTrace();
//          goto _L1
//        IOException ioexception2;
//        ioexception2;
//        s2 = s1;
//        ioexception2.printStackTrace();
//        try
//        {
//            bufferedreader.close();
//        }
//        catch(IOException ioexception3)
//        {
//            ioexception3.printStackTrace();
//        }
//          goto _L2
//        Exception exception;
//        exception;
//        try
//        {
//            bufferedreader.close();
//        }
//        catch(IOException ioexception1)
//        {
//            ioexception1.printStackTrace();
//        }
//        throw exception;
//        s2 = s1;
//          goto _L3
        
        return s3;
    }
}
