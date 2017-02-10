package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.oidiotlin.classmanager.utils.system.AppUtils.getVersionCode;
import static com.oidiotlin.classmanager.utils.system.Constant.API_CHECKUPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_CLIENT;

/**
 * Created by OIdiot on 2017/2/9.
 * Project: ClassManager
 */

public class CheckVersionTask implements Runnable {
    private static final String TAG = "CheckVersionTask";
    private Context context = null;
    private Handler handler;
    private AppInfo remoteInfo;

    public CheckVersionTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(SERVER_API + API_CHECKUPDATE);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(4000);
            InputStream inputStream = connection.getInputStream();
            remoteInfo = (new AppInfoParser()).parse(inputStream).get(0);

            int remoteVersion = remoteInfo.getVersionCode();
            int localVersion = getVersionCode(context);
            Log.i(TAG, "run: remoteVer = " + remoteVersion + "localVer = " + localVersion);

        } catch (Exception e) {
            Message msg = new Message();
            msg.what = UPDATE_CLIENT;
            msg.obj = remoteInfo.getNewFeatures();
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }
}
