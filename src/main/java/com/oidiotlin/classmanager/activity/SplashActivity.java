package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.Constant;
import com.oidiotlin.classmanager.utils.DatabaseManager;
import com.oidiotlin.classmanager.view.OverWatchLoading;

import java.io.File;

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

        if(!isOnline(this)){
            Toast.makeText(this, R.string.info_connecting_err, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

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
        //loadingAnimation.startAnim();

    }

    private static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                if(info.getState() == NetworkInfo.State.CONNECTED)
                    return true;
            }
        }
        return false;
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
                Log.i(TAG, "doInBackground: dir:");
                String[] lst = client.listNames();
                for (String x: lst) {
                    Log.i(TAG, "doInBackground: "+x);
                }
                File localDir = new File(Constant.DATABASE_PATH);
                if (!localDir.exists())
                    localDir.mkdirs();
                File localFile = new File(Constant.DATABASE_PATH + Constant.DATABASE_NAME);
                if (!localFile.exists())
                    localFile.createNewFile();
                client.download(Constant.DATABASE_PATH_ON_SERVER + Constant.DATABASE_NAME, localFile);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                R.string.info_download_success,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                R.string.info_connecting_err,
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                return null;
            }
            mMainHandler.sendEmptyMessageDelayed(0,3500);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
