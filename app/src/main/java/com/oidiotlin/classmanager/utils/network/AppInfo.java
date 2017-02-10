package com.oidiotlin.classmanager.utils.network;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class AppInfo {
    private int versionCode;
    private String versionName;
    private String newFeatures;

    public String getNewFeatures() {
        return newFeatures;
    }

    public void setNewFeatures(String newFeatures) {
        this.newFeatures = newFeatures;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String toString() {
        return "Application Information: versionCode - " + versionCode
                + " versionName - " + versionName;
    }
}
