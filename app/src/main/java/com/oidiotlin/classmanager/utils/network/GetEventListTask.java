package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;

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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
