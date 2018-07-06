package com.safeclass.xhg.xhg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by tomdxs on 2018/5/24.
 */

public class SplashActivity extends Activity {


    private final int SPLASH_DISPLAY_LENGHT = 1000;
    private RelativeLayout layout;
    private Handler handler;
    private Runnable runnable;

    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new RelativeLayout(this);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(layout);

        layout.setBackgroundResource(R.mipmap.startimg);

        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
            Intent intent = new Intent(SplashActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
            }
        };

        // 延迟SPLASH_DISPLAY_LENGHT时间然后执行runnable
        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGHT);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        super.onKeyDown(keyCode, event);
        if(keyCode == KeyEvent.KEYCODE_BACK){
            handler.removeCallbacks(runnable);
            this.finish();
        }
        return true;
    }

}

