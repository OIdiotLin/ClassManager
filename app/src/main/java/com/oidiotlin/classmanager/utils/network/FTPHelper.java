package com.oidiotlin.classmanager.utils.network;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;

/**
 * Created by OIdiot on 2017/2/9.
 * Project: ClassManager
 */

public class FTPHelper {
    private static FTPClient client;
    /**
     * Download file from remote FTP-server to local direction.
     * @param localDir Target Direction
     * @param localName Target Filename
     * @param remoteDir Source Direction on remote server
     * @param remoteName Source Filename on remote server
     */
    public static void downloadFile(String remoteDir, String remoteName,
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
