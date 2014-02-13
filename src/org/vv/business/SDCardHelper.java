// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import android.os.Environment;
import java.io.File;

public class SDCardHelper
{

    public SDCardHelper()
    {
    }

    public static String getCacheDir()
    {
        File file = Environment.getExternalStorageDirectory();
        File file1 = new File((new StringBuilder()).append(file).append("/childstory/").toString());
        if(!file1.exists())
            file1.mkdirs();
        return (new StringBuilder()).append(file).append("/childstory/").toString();
    }

    public static boolean hasSDCard()
    {
        boolean flag;
        if(Environment.getExternalStorageState().equals("mounted"))
            flag = true;
        else
            flag = false;
        return flag;
    }
}
