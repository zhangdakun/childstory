// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkDetector
{

    public NetworkDetector()
    {
    }

    public static boolean detect(Activity activity)
    {
        ConnectivityManager connectivitymanager = (ConnectivityManager)activity.getApplicationContext().getSystemService("connectivity");
        boolean flag;
        if(connectivitymanager == null)
        {
            flag = false;
        } else
        {
            NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
            if(networkinfo == null || !networkinfo.isAvailable())
                flag = false;
            else
                flag = true;
        }
        return flag;
    }
}
