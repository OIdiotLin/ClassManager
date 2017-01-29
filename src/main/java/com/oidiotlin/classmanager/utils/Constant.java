package com.oidiotlin.classmanager.utils;

import android.os.Environment;

/**
 * Created by OIdiot on 2016/12/17.
 * Project: ClassManager
 */

public class Constant {

    public static final String DATABASE_PATH = Environment.getExternalStorageDirectory() + "/ClassManager/";
    public static final String DATABASE_PATH_ON_SERVER = "./";
    public static final String DATABASE_NAME = "class164.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "student";
    public static final String ID = "Id";
    public static final String STUDENT_NUMBER = "StudentNumber";
    public static final String NAME = "Name";
    public static final String PINYIN = "PinYin";
    public static final String GENDER = "Gender";
    public static final String NATIVE_PROVINCE = "Gender";
    public static final String DORMITORY = "Dormitory";
    public static final String BIRTHDAY = "Birthday";
    public static final String PHONE_NUMBER = "PhoneNumber";
    public static final String POSITION = "Position";
    public static final String PARTICIPATION = "Participation";

    public static final String FTP_ADDRESS = "ftp.oidiotlin.com";
    public static final String FTP_USERNAME = "classmanager@oidiotlin.com";
    public static final String FTP_PASSWORD = "se164scut";
}
