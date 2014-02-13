// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.children.story2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// Referenced classes of package org.vv.children.story2:
//            MainActivity

public class WelcomeActivity extends Activity
{

    public WelcomeActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030004);
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

//            final WelcomeActivity this$0;
//
//            
//            {
//                this$0 = WelcomeActivity.this;
//                super();
//            }
        }
, 1500L);
    }
}
