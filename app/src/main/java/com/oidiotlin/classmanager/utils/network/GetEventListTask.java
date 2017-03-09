package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.oidiotlin.classmanager.utils.system.Constant.API_GET_EVENT_LIST;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_STATUS_OK;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_TIMEOUT;

/**
 * Created by OIdiot on 2017/3/9.
 * Project: ClassManager
 */

public class GetEventListTask implements Runnable {

    public static final String TAG = "GetEventListTask";
    private Context context;
    private Handler handler;

    public GetEventListTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(SERVER_API + API_GET_EVENT_LIST);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(SERVER_RESPONSE_TIMEOUT);

            if (connection.getResponseCode() == SERVER_RESPONSE_STATUS_OK) {
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024*128];
                int currentReadLength = 0;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((currentReadLength = inputStream.read(buffer)) > -1) {
                    baos.write(buffer, 0, currentReadLength);
                }
                String result = baos.toString();
                Log.i(TAG, "run: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
