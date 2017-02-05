package com.oidiotlin.classmanager.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.io.File;
import java.io.FileInputStream;

import it.sauronsoftware.ftp4j.FTPClient;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class UpdateHelper {
    private AppInfo localInfo;
    private Boolean appNeedUpdate;
    private Boolean databaseNeedUpdate;
    private Context context;

    public UpdateHelper(AppInfo localInfo,
                        Boolean appNeedUpdate, Boolean databaseNeedUpdate,
                        Context context) {
        this.localInfo = localInfo;
        this.appNeedUpdate = appNeedUpdate;
        this.databaseNeedUpdate = databaseNeedUpdate;
        this.context = context;
    }

    private void checkUpdate () {
        try {
            File infoFile = new File(Constant.APPINFO_PATH + Constant.APPINFO_XML);
            FileInputStream input = new FileInputStream(infoFile);
            AppInfoParser parser = new AppInfoParser();
            AppInfo remoteInfo = parser.parse(input).get(0);
            localInfo = new AppInfo();
            localInfo.setAppVer(getVersionCode(context));
            // TODO 获取 database 版本号
            //localInfo.setDbVer();

            if (localInfo.getAppVer() < remoteInfo.getAppVer()) {
                // TODO UI
                AppUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO
    private void AppUpdate () {

    }

    public int getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Download file from remote FTP-server to local direction.
     * @param client FTP-Client
     * @param localDir Target Direction
     * @param localName Target Filename
     * @param remoteDir Source Direction on remote server
     * @param remoteName Source Filename on remote server
     */
    public void downloadFile(FTPClient client,
                              String remoteDir, String remoteName,
                              String localDir, String localName) {
        if (client == null)
            return;
        if (localDir == null || localName == null || remoteDir == null || remoteName == null)
            return;

        try {
            File localFolder = new File(localDir);
            if (!localFolder.exists())
                localFolder.mkdirs();
            File localFile = new File(localDir + localName);
            if (!localFile.exists())
                localFile.createNewFile();
            client.download(remoteDir + remoteName, localFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
