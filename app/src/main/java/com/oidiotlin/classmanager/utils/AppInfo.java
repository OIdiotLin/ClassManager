package com.oidiotlin.classmanager.utils;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class AppInfo {
    private int appVer;
    private int dbVer;

    public int getAppVer() {
        return appVer;
    }

    public void setAppVer(int appVer) {
        this.appVer = appVer;
    }

    public int getDbVer() {
        return dbVer;
    }

    public void setDbVer(int dbVer) {
        this.dbVer = dbVer;
    }

    @Override
    public String toString() {
        return "Application Information: app version - " + appVer
                + " database version - " + dbVer;
    }
}
