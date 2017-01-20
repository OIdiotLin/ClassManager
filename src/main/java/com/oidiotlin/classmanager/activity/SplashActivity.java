package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.Constant;
import com.oidiotlin.classmanager.utils.DatabaseManager;
import com.oidiotlin.classmanager.view.OverWatchLoading;

import it.sauronsoftware.ftp4j.FTPClient;


public class SplashActivity extends Activity {

    private ImageView logo;
    private ImageView text;
    private TextView copyright;
    private OverWatchLoading loadingAnimation;

    private FTPClient client;

    private Handler mMainHandler = new Handler() {
        public void handleMessage(Message message) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClass(getApplication(), MainActivity.class);
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

        logo = (ImageView) findViewById(R.id.splash_logo);
        text = (ImageView) findViewById(R.id.splash_text);
        copyright = (TextView) findViewById(R.id.splash_copyright);
        loadingAnimation = (OverWatchLoading) findViewById(R.id.owloading);

        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);   // 渐变动画持续时间
        alphaAnimation.setStartOffset(800);    // 渐变动画延时

        new myAsyncTask().execute();

        logo.setAnimation(alphaAnimation);
        text.setAnimation(alphaAnimation);
        copyright.setAnimation(alphaAnimation);
        //loadingAnimation.startAnim();   //TODO 修复启动失败问题

        mMainHandler.sendEmptyMessageDelayed(0,5000);
    }

    //TODO 异步任务：Splash_Activity中加载数据库
    class myAsyncTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            //TODO 同步服务器上的 database
            final String TAG = "FTP";
            DatabaseManager dbManager;
            client = new FTPClient();
            try{
                client.connect(Constant.FTP_ADDRESS);
                client.login(Constant.FTP_USERNAME, Constant.FTP_PASSWORD);
                Log.i(TAG, "doInBackground: connecting");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
