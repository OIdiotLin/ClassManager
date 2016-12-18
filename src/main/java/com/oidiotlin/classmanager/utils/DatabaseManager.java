package com.oidiotlin.classmanager.utils;

import android.content.Context;

/**
 * Created by OIdiot on 2016/12/17.
 * Project: ClassManager
 */

public class DatabaseManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context) {
        if(helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }
}
