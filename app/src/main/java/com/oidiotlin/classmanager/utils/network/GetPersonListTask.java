package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;

/**
 * Created by OIdiot on 2017/2/17.
 * Project: ClassManager
 */

public class GetPersonListTask implements Runnable {
    private final String TAG = "GetPersonListTask";
    private Context context;
    private Handler handler;

    public GetPersonListTask(Context context, Handler handler) {
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
