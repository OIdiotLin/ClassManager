package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.oidiotlin.classmanager.utils.parser.UrlParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.Process.killProcess;
import static android.os.Process.myPid;
import static com.oidiotlin.classmanager.utils.system.AppUtils.isSdCardOK;
import static com.oidiotlin.classmanager.utils.system.Constant.API_GET_APK_URL;
import static com.oidiotlin.classmanager.utils.system.Constant.APP_FILE_PATH;
import static com.oidiotlin.classmanager.utils.system.Constant.DOWNLOAD_ERROR;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_TIMEOUT;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_APK_NAME;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_DISMISS;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_SETMAX;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_SHOW;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_UPDATEPROGRESS;


/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public class UpdateAppTask implements Runnable {

    private final String TAG = "UpdateAppTask";
    private Context context;
    private Handler handler;

    public UpdateAppTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        /*
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Downloading...");
        progressDialog.show();*/
        /**
         * show the update dialog
         */
        Message msg = new Message();
        msg.what = UPDATE_DIALOG;
        msg.arg1 = UPDATE_DIALOG_SHOW;
        handler.sendMessage(msg);

        new Thread() {  // 下载并安装
            @Override
            public void run() {
                try {
                    URL url = new URL(SERVER_API + API_GET_APK_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    url = (new UrlParser()).parse(inputStream).get(0);
                    Log.i(TAG, "run: new apk url = " + url.toString());
                    File apkFile = getFileFromServer(url);
                    installApk(apkFile);
                    /**
                     * dismiss the update dialog
                     */
                    Message msg = new Message();
                    msg.what = UPDATE_DIALOG;
                    msg.arg1 = UPDATE_DIALOG_DISMISS;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWNLOAD_ERROR;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.run();

    }

    private File getFileFromServer(URL url) throws Exception {
        if (isSdCardOK()) {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            connection.setConnectTimeout(SERVER_RESPONSE_TIMEOUT);
            //pd.setMax(connection.getContentLength());   // set 100% progress size

            /**
             * 返回给 UI 设置 ProgressDialog 最大值
             */
            Message msg = new Message();
            msg.what = UPDATE_DIALOG;
            msg.arg1 = UPDATE_DIALOG_SETMAX;
            msg.arg2 = connection.getContentLength();
            handler.sendMessage(msg);

            File file = new File(APP_FILE_PATH + UPDATE_APK_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len, total = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
                total += len;

                Message msg1 = new Message();
                msg1.what = UPDATE_DIALOG;
                msg1.arg1 = UPDATE_DIALOG_UPDATEPROGRESS;
                msg1.arg2 = total;
                handler.sendMessage(msg1);
            }
            fileOutputStream.close();
            inputStream.close();
            bufferedInputStream.close();
            return file;
        }
        return null;
    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
        killProcess(myPid());
    }
}
