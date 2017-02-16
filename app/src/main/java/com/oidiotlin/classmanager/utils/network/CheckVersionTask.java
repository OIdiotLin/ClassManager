package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.oidiotlin.classmanager.utils.system.AppUtils.getVersionCode;
import static com.oidiotlin.classmanager.utils.system.Constant.API_CHECK_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.FORCED_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.NO_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.OPTIONAL_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_TIMEOUT;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_CLIENT;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_ERROR;
import static com.oidiotlin.classmanager.utils.system.Constant.VERSION_MAX_DIFF_TOLERANCE;

/**
 * Created by OIdiot on 2017/2/9.
 * Project: ClassManager
 */

public class CheckVersionTask implements Runnable {
    private static final String TAG = "CheckVersionTask";
    private Context context = null;
    private Handler handler;

    public CheckVersionTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            URL url = new URL(SERVER_API + API_CHECK_UPDATE);
            Log.i(TAG, "run: "+url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(SERVER_RESPONSE_TIMEOUT);
            InputStream inputStream = connection.getInputStream();
            AppInfo remoteInfo = (new AppInfoParser()).parse(inputStream).get(0);

            int remoteVersion = remoteInfo.getVersionCode();
            int localVersion = getVersionCode(context);
            Log.i(TAG, "run: " + remoteInfo.toString());

            if (remoteVersion - localVersion > VERSION_MAX_DIFF_TOLERANCE) {    // Forced Update
                Message msg = new Message();
                msg.what = UPDATE_CLIENT;
                msg.arg1 = FORCED_UPDATE;
                msg.obj = remoteInfo;
                handler.sendMessage(msg);
            } else if (remoteVersion > localVersion) {      // Choose to update or not
                Message msg = new Message();
                msg.what = UPDATE_CLIENT;
                msg.arg1 = OPTIONAL_UPDATE;
                msg.obj = remoteInfo;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = UPDATE_CLIENT;
                msg.arg1 = NO_UPDATE;
                handler.sendMessage(msg);
            }

        } catch (Exception e) {
            Message msg = new Message();
            msg.what = UPDATE_ERROR;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }
}
