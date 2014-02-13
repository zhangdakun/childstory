// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.children.story2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import java.io.*;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import org.vv.business.*;

import com.android.adlib.AD;
import com.android.adlib.ADbaseActivity;
import com.android.mychildstory.R;

// Referenced classes of package org.vv.children.story2:
//            Fetch

public class PlayActivity extends ADbaseActivity
{
    class MyHandlerCallback
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
//            message.what;
//            JVM INSTR tableswitch 4096 4097: default 28
//        //                       4096 30
//        //                       4097 103;
//               goto _L1 _L2 _L3
//_L1:
//            return true;
//_L2:
//            progressDialog = new ProgressDialog(PlayActivity.this);
//            progressDialog.setTitle(0x7f04000d);
//            progressDialog.setMessage(getString(0x7f04001c));
//            progressDialog.setProgressStyle(0);
//            progressDialog.show();
//            continue; /* Loop/switch isn't completed */
//_L3:
//            ibPlay.setEnabled(true);
//            ibStop.setEnabled(true);
//            tvContent.setText(Html.fromHtml(content));
//            progressDialog.dismiss();
//            if(true) goto _L1; else goto _L4
//_L4:
        	try {
				

        	switch(message.what) {
	        	case 4096:
	        	try{
		            if(progressDialog != null ) {
		            	progressDialog.dismiss();
		            }	
	              progressDialog = new ProgressDialog(context);
	              progressDialog.setTitle(0x7f04000d);
	              progressDialog.setMessage(getString(0x7f04001c));
	              progressDialog.setProgressStyle(0);
	              progressDialog.show();
	        	}catch(Exception e) {
	        		e.printStackTrace();
	        	}
	        		break;
	        	case 4097:
	              ibPlay.setEnabled(true);
	              ibStop.setEnabled(true);
	              tvContent.setText(Html.fromHtml(content));
	              if(progressDialog != null ) {
	              progressDialog.dismiss();
	              progressDialog = null;
	              }
	        		break;
        	}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        	return true;
        }

//        final PlayActivity this$0;
//
//        MyHandlerCallback()
//        {
//            this$0 = PlayActivity.this;
//            super();
//        }
    }


    public PlayActivity()
    {
        isStart = false;
        handler = new Handler(new MyHandlerCallback());
    }

    private void load()
    {
        handler.sendEmptyMessage(4096);
        (new Thread(new Runnable() {

            public void run()
            {
                String s = MD5Utils.toMd5(nextUrl);
                File file = new File((new StringBuilder()).append(SDCardHelper.getCacheDir()).append(s).append(".txt").toString());
                String s1;
                File file1;
                if(file.exists())
                {
                    content = CacheService.readTxt(file);
                    if( null == content||"".equals(content)) {
                        System.out.println(nextUrl);
                        content = Fetch.fetchContent(nextUrl);
                        CacheService.writeTxt(file, content);
                    }
                } else
                {
                    System.out.println(nextUrl);
                    content = Fetch.fetchContent(nextUrl);
                    CacheService.writeTxt(file, content);
                }
                
                if( null == content||"".equals(content)) {
                	return; // lieb add 
                }
                if(ChineseCode.isTW())
                    content = ChineseCode.toLong(content);
                s1 = (new StringBuilder()).append(SDCardHelper.getCacheDir()).append(s).append(".mp3").toString();
                file1 = new File(s1);
//                if(!file1.exists()) goto _L2; else goto _L1
//_L1:
//                mediaPlayer.setDataSource(s1);
//_L3:
//                mediaPlayer.prepare();
//                mediaPlayer.seekTo(12000);
//_L4:
//                handler.sendEmptyMessage(4097);
//                return;
//_L2:
//                mediaPlayer.setDataSource(Fetch.getMP3URL(nextUrl));
//                  goto _L3
//                IllegalArgumentException illegalargumentexception;
//                illegalargumentexception;
//                illegalargumentexception.printStackTrace();
//                  goto _L4
//                IllegalStateException illegalstateexception;
//                illegalstateexception;
//                illegalstateexception.printStackTrace();
//                  goto _L4
//                IOException ioexception;
//                ioexception;
//                ioexception.printStackTrace();
//                  goto _L4
                try {

					if (file1.exists()&&file.length() > 0) {
						mediaPlayer.setDataSource(s1);
					} else {
						mediaPlayer.setDataSource(Fetch.getMP3URL(nextUrl));
					}
					
					mediaPlayer.prepare();
					mediaPlayer.seekTo(12000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				handler.sendEmptyMessage(4097);
              return;
                
            }

//            final PlayActivity this$0;
//
//            
//            {
//                this$0 = PlayActivity.this;
//                super();
//            }
        }
)).start();
    }

    Context context;
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030003);
        context = this;
//		LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
//		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
//		adLayout.addView(adView);
		
        tvContent = (TextView)findViewById(0x7f070011);
        btnBack = (Button)findViewById(0x7f07000e);
        btnBack.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(mediaPlayer != null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                finish();
            }

//            final PlayActivity this$0;
//
//            
//            {
//                this$0 = PlayActivity.this;
//                super();
//            }
        }
);
        ibPlay = (ImageButton)findViewById(0x7f070013);
        ibStop = (ImageButton)findViewById(0x7f070014);
        ibPlay.setEnabled(false);
        ibStop.setEnabled(false);
        ibPlay.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(isStart)
                {
                    mediaPlayer.pause();
                    ibPlay.setImageResource(0x7f02002c);
                    isStart = false;
                    AD.showChaBo(context);
                } else
                {
                    mediaPlayer.start();
                    ibPlay.setImageResource(0x7f02002b);
                    isStart = true;
                }
            }

//            final PlayActivity this$0;
//
//            
//            {
//                this$0 = PlayActivity.this;
//                super();
//            }
        }
);
        ibStop.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(mediaPlayer != null)
                {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(12000);
                    ibPlay.setImageResource(0x7f02002c);
                    isStart = false;
                }
            }

//            final PlayActivity this$0;
//
//            
//            {
//                this$0 = PlayActivity.this;
//                super();
//            }
        }
);
        Bundle bundle1 = getIntent().getExtras();
        nextUrl = bundle1.getString("nextUrl");
        name = bundle1.getString("name");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mediaplayer)
            {
                mediaPlayer.pause();
                mediaPlayer.seekTo(12000);
                ibPlay.setImageResource(0x7f02002c);
                isStart = false;
            }

//            final PlayActivity this$0;
//
//            
//            {
//                this$0 = PlayActivity.this;
//                super();
//            }
        }
);
        load();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            if(mediaPlayer != null)
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            finish();
        }
        return super.onKeyDown(i, keyevent);
    }

    Button btnBack;
    String content;
    Handler handler;
    ImageButton ibPlay;
    ImageButton ibStop;
    boolean isStart;
    MediaPlayer mediaPlayer;
    String mp3LinkUrl;
    String name;
    String nextUrl;
    ProgressDialog progressDialog;
    TextView tvContent;
}
