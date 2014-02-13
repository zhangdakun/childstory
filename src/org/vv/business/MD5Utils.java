// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils
{

    public MD5Utils()
    {
    }

    private static String toHexString(byte abyte0[], String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = abyte0.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(Integer.toHexString(0xff & abyte0[j])).append(s);

        return stringbuilder.toString();
    }

    public static String toMd5(String s)
    {
        String s2 = null;;
        MessageDigest messagedigest;
		try {
			messagedigest = MessageDigest.getInstance("MD5");

        messagedigest.reset();
        messagedigest.update(s.getBytes("utf-8"));
        s2 = toHexString(messagedigest.digest(), "");
        String s1 = s2;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//_L2:
//        return s1;
//        UnsupportedEncodingException unsupportedencodingexception;
//        unsupportedencodingexception;
//        unsupportedencodingexception.printStackTrace();
//_L3:
//        s1 = null;
//        if(true) goto _L2; else goto _L1
//_L1:
//        NoSuchAlgorithmException nosuchalgorithmexception;
//        nosuchalgorithmexception;
//        nosuchalgorithmexception.printStackTrace();
//          goto _L3
 catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return s2;
    }
}
