package com.oidiotlin.classmanager.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OIdiot on 2016/12/17.
 * Project: ClassManager
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    /**
     * Constructor
     * @param context
     * @param name name of the database
     * @param factory cursorFactory (like a pointer)
     * @param version version number (>=1)
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, 1);
    }

    /**
     * recall when open database
     * @param db the Database opened
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * recall when database upgraded,
     * @param sqLiteDatabase the Database Editing
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    /**
     * recall when database created.
     * @param sqLiteDatabase the Database Editing
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
}
