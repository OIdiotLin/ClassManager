package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.oidiotlin.classmanager.R;

public class SplashActivity extends Activity {

    private Handler mMainHandler = new Handler() {
        public void handleMessage(Message message) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(getApplication(), OverviewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mMainHandler.sendEmptyMessageDelayed(0,3000);
    }
}
