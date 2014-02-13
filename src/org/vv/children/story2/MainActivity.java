// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.children.story2;

import android.app.*;
import android.content.*;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.*;
//import com.google.ads.*;
import java.io.*;
import java.util.*;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import org.apache.http.client.ClientProtocolException;
import org.vv.business.*;

import com.android.adlib.AD;
import com.android.adlib.ADbaseActivity;
import com.android.mychildstory.R;

// Referenced classes of package org.vv.children.story2:
//            MyAdapter, Fetch, PlayActivity

public class MainActivity extends ADbaseActivity
{
    class MyHandlerCallback
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
//            message.what;
//            JVM INSTR lookupswitch 5: default 56
//        //                       4096: 58
//        //                       4097: 131
//        //                       8192: 269
//        //                       8193: 288
//        //                       8194: 334;
//               goto _L1 _L2 _L3 _L4 _L5 _L6
//_L1:
//            return true;
//_L2:
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setTitle(0x7f040005);
//            progressDialog.setMessage(getString(0x7f040006));
//            progressDialog.setProgressStyle(0);
//            progressDialog.show();
//            continue; /* Loop/switch isn't completed */
//_L3:
//            adapter = new MyAdapter(list, MainActivity.this, download);
//            lvCatelog.setAdapter(adapter);
//            btnNavPage.setText((new StringBuilder()).append(currentPage).append(" / ").append(pageCounts[currentSwitch]).toString());
//            if(progressDialog != null && progressDialog.isShowing())
//                progressDialog.dismiss();
//            continue; /* Loop/switch isn't completed */
//_L4:
//            int i = message.arg1;
//            progressDialog.setProgress(i);
//            continue; /* Loop/switch isn't completed */
//_L5:
//            if(progressDialog != null && progressDialog.isShowing())
//                progressDialog.dismiss();
//            adapter.notifyDataSetChanged();
//            continue; /* Loop/switch isn't completed */
//_L6:
//            String s = (String)message.obj;
//            progressDialog.setMessage(s);
//            if(true) goto _L1; else goto _L7
//_L7:
        	switch ( message.what) {
			case 4096:
	            progressDialog = new ProgressDialog(context);
	            progressDialog.setTitle(0x7f040005);
	            progressDialog.setMessage(getString(0x7f040006));
	            progressDialog.setProgressStyle(0);
	            progressDialog.show();
				break;
			case 4097:
	            adapter = new MyAdapter(list, context, download);
	            lvCatelog.setAdapter(adapter);
	            btnNavPage.setText((new StringBuilder()).append(currentPage).append(" / ").append(pageCounts[currentSwitch]).toString());
	            if(progressDialog != null && progressDialog.isShowing())
	                progressDialog.dismiss();
				break;
				
			case 8192:
	            int i = message.arg1;
	            progressDialog.setProgress(i);
				break;
			case 8193:
				if (progressDialog != null && progressDialog.isShowing())
					progressDialog.dismiss();
				adapter.notifyDataSetChanged();
				break;
			case 8194:
	            String s = (String)message.obj;
	            progressDialog.setMessage(s);
				break;

			default:
				break;
			}
        	
        	return true;
        }

//        final MainActivity this$0;
//
//        MyHandlerCallback()
//        {
//            this$0 = MainActivity.this;
//            super();
//        }
    }


    public MainActivity()
    {
        handler = new Handler(new MyHandlerCallback());
        saveFileName = "page";
        networkState = false;
        String as[] = new String[6];
        as[0] = "http://story.beva.com/20/tag/yin-pin/";
        as[1] = "http://story.beva.com/21/tag/yin-pin/";
        as[2] = "http://story.beva.com/22/tag/yin-pin/";
        as[3] = "http://story.beva.com/23/tag/yin-pin/";
        as[4] = "http://story.beva.com/24/tag/yin-pin/";
        as[5] = "http://story.beva.com/25/tag/yin-pin/";
        catelogUrls = as;
        int ai[] = new int[6];
        ai[0] = 20;
        ai[1] = 25;
        ai[2] = 27;
        ai[3] = 26;
        ai[4] = 25;
        ai[5] = 24;
        pageCounts = ai;
        currentSwitch = 0;
        currentPage = 1;
        download = new MyAdapter.Download() {

            public void download(final String nextUrl, final String fileName)
            {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle(0x7f04000d);
                progressDialog.setMessage(getString(0x7f040019));
                progressDialog.setProgressStyle(1);
                progressDialog.setProgress(0);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                (new Thread(new Runnable() {

                    public void run()
                    {
                        DownloadService downloadservice;
                        String s1;
                        downloadservice = new DownloadService();
                        Message message = handler.obtainMessage();
                        message.what = 8194;
                        message.obj = getString(0x7f040018);
                        handler.sendMessage(message);
                        System.out.println(nextUrl);
                        try {
                        String s = Fetch.fetchContent(nextUrl);
                        CacheService.writeTxt(new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(fileName).append(".txt").toString()), s);
                        Message message1 = handler.obtainMessage();
                        message1.what = 8194;
                        message1.obj = getString(0x7f040017);
                        handler.sendMessage(message1);
                        
                        
                        	s1 = Fetch.getMP3URL(nextUrl);
							downloadservice.downloadFile(s1, SDCardHelper.getCacheDir(), (new StringBuilder()).append(fileName).append(".mp3").toString(), new org.vv.business.DownloadService.DownloadProcessCallBack() {

							    public void updateProcess(int i, long l)
							    {
							        Message message2 = handler.obtainMessage();
							        message2.what = 8192;
							        message2.arg1 = i;
							        handler.sendMessage(message2);
							        if(i == 100)
							            handler.sendEmptyMessage(8193);
							    }

//                            final _cls1 this$2;
//
//                        
//                        {
//                            this$2 = _cls1.this;
//                            super();
//                        }
							}
);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//_L1:
//                        return;
//                        ClientProtocolException clientprotocolexception;
//                        clientprotocolexception;
//                        clientprotocolexception.printStackTrace();
//                          goto _L1
//                        IOException ioexception;
//                        ioexception;
//                        ioexception.printStackTrace();
//                          goto _L1
                    }

//                    final _cls12 this$1;
//                    final String val$fileName;
//                    final String val$nextUrl;
//
//                    
//                    {
//                        this$1 = _cls12.this;
//                        nextUrl = s;
//                        fileName = s1;
//                        super();
//                    }
                }
)).start();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
;
    }

    private void doTask()
    {
        handler.sendEmptyMessage(4096);
        (new Thread(new Runnable() {

            public void run()
            {
                MainActivity mainactivity = MainActivity.this;
                String s = (new StringBuilder()).append(saveFileName).append("_").append(currentSwitch).append("_").append(currentPage).toString();
                String as[] = new String[3];
                as[0] = "name";
                as[1] = "tip";
                as[2] = "nextUrl";
                mainactivity.list = CacheService.read(s, as);
                System.out.println("1. read cache data");
                if(list.size() <= 0)
                {
                    list = Fetch.fetchCatelog((new StringBuilder()).append(catelogUrls[currentSwitch]).append(currentPage).toString());
                    System.out.println("2. fetch online data");
                    if(saveFileName != null)
                    {
                        CacheService.write(list, (new StringBuilder()).append(saveFileName).append("_").append(currentSwitch).append("_").append(currentPage).toString());
                        System.out.println("3. save cache data");
                    }
                }
                handler.sendEmptyMessage(4097);
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
)).start();
    }

    private void initNav()
    {
        tvNavs = new TextView[catelogNames.length];
        for(int i = 0; i < catelogNames.length; i++)
        {
            tvNavs[i] = new TextView(this);
            tvNavs[i].setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -2));
            tvNavs[i].setTextColor(0xff3f0000);
            tvNavs[i].setTextSize(2, 18F);
            tvNavs[i].setText(catelogNames[i]);
            tvNavs[i].setPadding(dip2px(5F), dip2px(5F), dip2px(5F), dip2px(5F));
            tvNavs[i].setTag(Integer.valueOf(i));
            tvNavs[i].setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    currentSwitch = ((Integer)view.getTag()).intValue();
                    showNav();
                    currentPage = 1;
                    doTask();
                }

//                final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
            }
);
            llNav.addView(tvNavs[i]);
        }

    }

    private int px(int i)
    {
        return (int)(0.5F + (float)i * scale);
    }

    private void showNav()
    {
        int i = 0;
        while(i < catelogNames.length) 
        {
            if(i == currentSwitch)
            {
                tvNavs[i].setTextColor(-1);
                tvNavs[i].setBackgroundColor(0xffbf005d);
            } else
            {
                tvNavs[i].setTextColor(0xff3f0000);
                tvNavs[i].setBackgroundColor(0);
            }
            i++;
        }
    }

    protected void dialog()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(0x7f040014);
        builder.setMessage(0x7f040013);
        builder.setPositiveButton(0x7f040014, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                finish();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
//        builder.setNeutralButton(0x7f040016, new android.content.DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialoginterface, int i)
//            {
//                Intent intent;
//                dialoginterface.dismiss();
//                intent = new Intent("android.intent.action.VIEW");
//                intent.setData(Uri.parse((new StringBuilder()).append("market://details?id=").append(getPackageName()).toString()));
//                startActivity(intent);
//            }
//
//
//        }
//);
        builder.setNegativeButton(0x7f040015, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        builder.create().show();
    }

    public int dip2px(float f)
    {
        return (int)(0.5F + f * scale);
    }
    
    Context context;
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030001);
		AdManager.getInstance(this).init("32f31ff5fb2d272d",
				"29ad1301694f1b8b", false);

		context = this;
//		LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
//		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
//		adLayout.addView(adView);
        	        
//        if((new Random()).nextInt(8) == 1)
//        {
//            final InterstitialAd interstitial = new InterstitialAd(this, "a15227bb03ba607");
//            AdRequest adrequest = new AdRequest();
//            interstitial.setAdListener(new AdListener() {
//
//                public void onDismissScreen(Ad ad)
//                {
//                }
//
//                public void onFailedToReceiveAd(Ad ad, com.google.ads.AdRequest.ErrorCode errorcode)
//                {
//                }
//
//                public void onLeaveApplication(Ad ad)
//                {
//                }
//
//                public void onPresentScreen(Ad ad)
//                {
//                }
//
//                public void onReceiveAd(Ad ad)
//                {
//                    interstitial.show();
//                }
//
//                final MainActivity this$0;
//                final InterstitialAd val$interstitial;
//
//            
//            {
//                this$0 = MainActivity.this;
//                interstitial = interstitialad;
//                super();
//            }
//            }
//);
//            interstitial.loadAd(adrequest);
//        }
        String as[] = new String[6];
        as[0] = getString(0x7f040007);
        as[1] = getString(0x7f040008);
        as[2] = getString(0x7f040009);
        as[3] = getString(0x7f04000a);
        as[4] = getString(0x7f04000b);
        as[5] = getString(0x7f04000c);
        catelogNames = as;
        if(!NetworkDetector.detect(this))
            DialogUtils.showConfirmWebDialog(this, getString(0x7f04000e), getString(0x7f04000d));
        scale = getResources().getDisplayMetrics().density;
        llNav = (LinearLayout)findViewById(0x7f070004);
        tvAppTitle = (TextView)findViewById(0x7f070002);
        lvCatelog = (ListView)findViewById(0x7f070005);
        btnUpdate = (Button)findViewById(0x7f070003);
        btnPrevious = (Button)findViewById(0x7f070007);
        btnNext = (Button)findViewById(0x7f070009);
        btnNavPage = (Button)findViewById(0x7f070008);
        btnPrevious.setEnabled(false);
        if(!NetworkDetector.detect(this))
        {
            DialogUtils.showConfirmWebDialog(this, getString(0x7f04000f), getString(0x7f04000d));
            btnUpdate.setEnabled(false);
        }
        btnUpdate.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                handler.sendEmptyMessage(4096);
                (new Thread(new Runnable() {

                    public void run()
                    {
                        list = Fetch.fetchCatelog((new StringBuilder()).append(catelogUrls[currentSwitch]).append(currentPage).toString());
                        System.out.println("2. fetch online data");
                        if(saveFileName != null)
                        {
                            CacheService.write(list, (new StringBuilder()).append(saveFileName).append("_").append(currentSwitch).append("_").append(currentPage).toString());
                            System.out.println("3. save cache data");
                        }
                        handler.sendEmptyMessage(4097);
                    }

//                    final _cls2 this$1;
//
//                    
//                    {
//                        this$1 = _cls2.this;
//                        super();
//                    }
                }
)).start();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        btnNavPage.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                LinearLayout linearlayout = new LinearLayout(MainActivity.this);
                linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
                linearlayout.setOrientation(0);
                linearlayout.setGravity(16);
                final TextView tvPage = new TextView(MainActivity.this);
                tvPage.setLayoutParams(new android.widget.LinearLayout.LayoutParams(px(40), -2));
                tvPage.setPadding(px(3), px(3), px(3), px(3));
                tvPage.setGravity(17);
                tvPage.setTextColor(-1);
                tvPage.setText(String.valueOf(currentPage));
                linearlayout.addView(tvPage);
                final SeekBar skbPage = new SeekBar(MainActivity.this);
                skbPage.setLayoutParams(new android.widget.LinearLayout.LayoutParams(0, -2, 1.0F));
                skbPage.setMax(-1 + pageCounts[currentSwitch]);
                skbPage.setProgress(-1 + currentPage);
                skbPage.setPadding(px(3), px(3), px(3), px(3));
                linearlayout.addView(skbPage);
                skbPage.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

                    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
                    {
                        tvPage.setText((new StringBuilder()).append("").append(i + 1).toString());
                    }

                    public void onStartTrackingTouch(SeekBar seekbar)
                    {
                    }

                    public void onStopTrackingTouch(SeekBar seekbar)
                    {
                    }

//                    final _cls3 this$1;
//                    final TextView val$tvPage;
//
//                    
//                    {
//                        this$1 = _cls3.this;
//                        tvPage = textview;
//                        super();
//                    }
                }
);
                (new android.app.AlertDialog.Builder(MainActivity.this)).setTitle(0x7f040010).setIcon(0x108009b).setView(linearlayout).setPositiveButton(getString(0x7f040011), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        currentPage = 1 + skbPage.getProgress();
                        if(currentPage == pageCounts[currentSwitch])
                            btnNext.setEnabled(false);
                        else
                            btnNext.setEnabled(true);
                        if(currentPage == 1)
                            btnPrevious.setEnabled(false);
                        else
                            btnPrevious.setEnabled(true);
                        dialoginterface.dismiss();
                        doTask();
                    }

//                    final _cls3 this$1;
//                    final SeekBar val$skbPage;
//
//                    
//                    {
//                        this$1 = _cls3.this;
//                        skbPage = seekbar;
//                        super();
//                    }
                }
).setNegativeButton(getString(0x7f040012), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

//                    final _cls3 this$1;
//
//                    
//                    {
//                        this$1 = _cls3.this;
//                        super();
//                    }
                }
).show();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        lvCatelog.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
            	if(i>8) {
            		if(!AD.i().haveOffers(context)) {
            			return;
            		}
            	}
                Map map = (Map)adapterview.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", (String)map.get("name"));
                bundle1.putString("nextUrl", (String)map.get("nextUrl"));
                intent.putExtras(bundle1);
                startActivity(intent);
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        btnPrevious.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                currentPage = -1 + currentPage;
                if(currentPage <= 1)
                    btnPrevious.setEnabled(false);
                btnNext.setEnabled(true);
                doTask();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        btnNext.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                currentPage = 1 + currentPage;
                if(currentPage >= pageCounts[currentSwitch])
                    btnNext.setEnabled(false);
                btnPrevious.setEnabled(true);
                doTask();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        initNav();
        showNav();
        doTask();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
        menu.add(0, 2, 1, getString(0x7f04001a)).setIcon(0x108004d);
        menu.add(0, 7, 6, getString(0x7f04001d)).setIcon(0x1080051);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = true;
//        Log.d("child", "keyevent.getRepeatCount(), "+keyevent.getRepeatCount());
        if(i == 4 && keyevent.getRepeatCount() == 0)
        {
            dialog();
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
//        menuitem.getItemId();
//        JVM INSTR lookupswitch 2: default 32
//    //                   2: 34
//    //                   7: 101;
//           goto _L1 _L2 _L3
//_L1:
//        return false;
//_L2:
//        if(SDCardHelper.hasSDCard())
//        {
//            File afile[] = (new File(SDCardHelper.getCacheDir())).listFiles();
//            int i = afile.length;
//            for(int j = 0; j < i; j++)
//                afile[j].delete();
//
//        }
//        DialogUtils.showMsgDialog(this, getString(0x7f04000d), getString(0x7f04001b));
//        continue; /* Loop/switch isn't completed */
//_L3:
//        dialog();
//        if(true) goto _L1; else goto _L4
//_L4:
    	switch (menuitem.getItemId()) {
		case 2:
	        if(SDCardHelper.hasSDCard())
	        {
	            File afile[] = (new File(SDCardHelper.getCacheDir())).listFiles();
	            int i = afile.length;
	            for(int j = 0; j < i; j++)
	                afile[j].delete();
	
	        }
	        DialogUtils.showMsgDialog(this, getString(0x7f04000d), getString(0x7f04001b));
			break;
		case 7:
			dialog();
			break;
		default:
			break;
		}
    	return super.onOptionsItemSelected(menuitem);
    }

    public int px2dip(float f)
    {
        return (int)(0.5F + f / scale);
    }

    MyAdapter adapter;
    Button btnNavPage;
    Button btnNext;
    Button btnPrevious;
    Button btnUpdate;
    private String catelogNames[];
    private String catelogUrls[];
    int currentPage;
    private int currentSwitch;
    MyAdapter.Download download;
    Handler handler;
    List list;
    LinearLayout llNav;
    ListView lvCatelog;
    boolean networkState;
    private int pageCounts[];
    ProgressDialog progressDialog;
    String saveFileName;
    float scale;
    TextView tvAppTitle;
    TextView tvNavs[];




/*
    static int access$102(MainActivity mainactivity, int i)
    {
        mainactivity.currentSwitch = i;
        return i;
    }

*/




}
