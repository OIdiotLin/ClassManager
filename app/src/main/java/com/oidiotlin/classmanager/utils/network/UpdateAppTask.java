package com.oidiotlin.classmanager.utils.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static com.oidiotlin.classmanager.utils.system.AppUtils.isSdCardOK;
import static com.oidiotlin.classmanager.utils.system.Constant.API_GET_APK_URL;
import static com.oidiotlin.classmanager.utils.system.Constant.APP_FILE_PATH;
import static com.oidiotlin.classmanager.utils.system.Constant.DOWNLOAD_ERROR;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_TIMEOUT;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_APK_NAME;


/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public class UpdateAppTask implements Runnable {
    private Context context;
    private Handler handler;
    public UpdateAppTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Downloading...");
        progressDialog.show();
        new Thread() {  // 下载并安装
            @Override
            public void run() {
                try {
                    URL url = new URL(SERVER_API + API_GET_APK_URL);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    url = (new UrlParser()).parse(inputStream).get(0);
                    File apkFile = getFileFromServer(url, progressDialog);
                    installApk(apkFile);
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWNLOAD_ERROR;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.run();
    }

    private File getFileFromServer(URL url, ProgressDialog pd) throws Exception {
        if (isSdCardOK()) {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            connection.setConnectTimeout(SERVER_RESPONSE_TIMEOUT);
            pd.setMax(connection.getContentLength());   // set 100% progress size
            File file = new File(APP_FILE_PATH + UPDATE_APK_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len, total = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
                total += len;
                pd.setProgress(total);
            }
            fileOutputStream.close();
            inputStream.close();
            bufferedInputStream.close();
            return file;
        }
        return null;
    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(file));
        intent.setType("application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
