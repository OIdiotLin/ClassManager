package com.oidiotlin.classmanager.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    public static void execSQL (SQLiteDatabase db, String sql) {
        if(db != null) {
            if(sql != null && !sql.equals("")) {
                db.execSQL(sql);
            }
        }
    }
}
