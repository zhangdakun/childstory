// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.children.story2;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.io.File;
import java.util.*;
import org.vv.business.*;

public class MyAdapter extends BaseAdapter
{
    public static interface Download
    {

        public abstract void download(String s, String s1);
    }


    public MyAdapter(List list1, Context context1, Download download1)
    {
        list = new ArrayList();
        context = context1;
        list = list1;
        download = download1;
        mInflater = (LayoutInflater)context1.getSystemService("layout_inflater");
    }

    public int getCount()
    {
        return list.size();
    }

    public Object getItem(int i)
    {
        return list.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        Map map = (Map)list.get(i);
        final String nextUrl = (String)map.get("nextUrl");
        String s = (String)map.get("tip");
        String s1 = (String)map.get("name");
        View view1 = mInflater.inflate(0x7f030002, null);
        TextView textview = (TextView)view1.findViewById(0x7f07000b);
        TextView textview1 = (TextView)view1.findViewById(0x7f07000c);
        ImageView imageview;
        final String fileName;
        File file;
        File file1;
        if(ChineseCode.isTW())
        {
            textview.setText(ChineseCode.toLong(s1));
            textview1.setText(ChineseCode.toLong(s));
        } else
        {
            textview.setText(s1);
            textview1.setText(s);
        }
        imageview = (ImageView)view1.findViewById(0x7f07000d);
        fileName = MD5Utils.toMd5(nextUrl);
        file = new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(fileName).append(".mp3").toString());
        file1 = new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(fileName).append(".txt").toString());
        if(file.exists() && file1.exists())
        {
            imageview.setVisibility(8);
        } else
        {
            imageview.setVisibility(0);
            imageview.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view2)
                {
                    download.download(nextUrl, fileName);
                }

//                final MyAdapter this$0;
//                final String val$fileName;
//                final String val$nextUrl;
//
//            
//            {
//                this$0 = MyAdapter.this;
//                nextUrl = s;
//                fileName = s1;
//                super();
//            }
            }
);
        }
        return view1;
    }

    Context context;
    Download download;
    List list;
    LayoutInflater mInflater;
}
