// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class DialogUtils
{

    public DialogUtils()
    {
    }

    public static void showConfirmWebDialog(final Activity activity, String s, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setMessage(s1);
        builder.setIcon(0x108009b);
        builder.setTitle(s);
        builder.setPositiveButton("\u662F", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                activity.startActivityForResult(new Intent("android.settings.WIRELESS_SETTINGS"), 0);
                dialoginterface.dismiss();
            }

//            final Activity val$activity;
//
//            
//            {
//                activity = activity1;
//                super();
//            }
        }
);
        builder.setNegativeButton("\u5426", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

        }
);
        builder.create().show();
    }

    public static void showMsgDialog(Activity activity, String s, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setMessage(s1);
        builder.setIcon(0x108009b);
        builder.setTitle(s);
        builder.setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

        }
);
        builder.create().show();
    }
}
