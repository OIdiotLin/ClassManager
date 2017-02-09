package com.oidiotlin.classmanager.utils.network;

import com.oidiotlin.classmanager.utils.system.Constant;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by OIdiot on 2017/2/9.
 * Project: ClassManager
 */

public class CheckVersionTask implements Runnable {
    @Override
    public void run() {
        try {
            URL url = new URL(Constant.SERVER_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            AppInfoParser parser = new AppInfoParser();
            AppInfo appInfo = (parser.parse(is)).get(0);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
