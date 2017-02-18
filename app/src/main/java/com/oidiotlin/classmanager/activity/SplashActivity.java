package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.network.CheckVersionTask;
import com.oidiotlin.classmanager.utils.network.GetPersonListTask;
import com.oidiotlin.classmanager.utils.network.UpdateAppTask;
import com.oidiotlin.classmanager.utils.parser.AppInfo;
import com.oidiotlin.classmanager.utils.parser.Person;
import com.oidiotlin.classmanager.view.UpdateDialog;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.oidiotlin.classmanager.utils.system.AppUtils.isOnline;
import static com.oidiotlin.classmanager.utils.system.Constant.FORCED_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK_FAIL;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK_SUCCESS;
import static com.oidiotlin.classmanager.utils.system.Constant.NO_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.OPTIONAL_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_CLIENT;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_DISMISS;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_SETMAX;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_SHOW;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_DIALOG_UPDATEPROGRESS;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PERSON;
import static java.lang.Thread.sleep;


public class SplashActivity extends Activity {

    private final String TAG = "SplashActivity";
    private ImageView logo;
    private ImageView text;
    private TextView copyright;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        if(!isOnline(this)){
            Toast.makeText(this, R.string.info_offline, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        logo = (ImageView) findViewById(R.id.splash_logo);
        text = (ImageView) findViewById(R.id.splash_text);
        copyright = (TextView) findViewById(R.id.splash_copyright);

        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);   // 渐变动画持续时间
        alphaAnimation.setStartOffset(800);    // 渐变动画延时

        new Thread(new CheckVersionTask(SplashActivity.this, handler)).start();

        logo.setAnimation(alphaAnimation);
        text.setAnimation(alphaAnimation);
        copyright.setAnimation(alphaAnimation);

        progressDialog = new ProgressDialog(SplashActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Downloading...");
    }

    /**
     * 处理子线程中返回的 msg
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) { // 筛选线程
                case UPDATE_CLIENT: // 线程 - 检查更新
                    switch (msg.arg1) {
                        /**
                         * 存在新版本，提示用户是否选择更新
                         */
                        case OPTIONAL_UPDATE:   // 可选的更新
                            new UpdateDialog(SplashActivity.this, handler, (AppInfo)msg.obj).show();
                            break;
                        /**
                         * 版本差异过大，强制更新，直接开始下载进程
                         * @see UpdateAppTask
                         */
                        case FORCED_UPDATE:
                            Toast.makeText(SplashActivity.this,
                                    R.string.forced_update_toast, Toast.LENGTH_SHORT).show();
                            new Thread(new UpdateAppTask(SplashActivity.this, handler)).start();
                            break;
                        /**
                         * 无更新，直接跳转到下一个 Activity
                         * @see  MainActivity
                         */
                        case NO_UPDATE:
                            new Thread(new GetPersonListTask(SplashActivity.this, handler)).start();
                            break;
                    }
                    break;  // case UPDATE_CLIENT

                /**
                 * Handle ProgressDialog in UpdateAppTask
                 */
                case UPDATE_DIALOG:
                    switch (msg.arg1) {
                        /**
                         * 显示更新进度对话框
                         */
                        case UPDATE_DIALOG_SHOW:
                            progressDialog.show();
                            break;
                        /**
                         * 关闭对话框
                         */
                        case UPDATE_DIALOG_DISMISS:
                            progressDialog.dismiss();
                            break;
                        /**
                         * 设置最大值
                         */
                        case UPDATE_DIALOG_SETMAX:
                            Log.i(TAG, "handleMessage: setMax: " + msg.arg2);
                            progressDialog.setMax(msg.arg2);
                            Log.i(TAG, "handleMessage: getMax: " + progressDialog.getMax());
                            break;
                        /**
                         * 更新进度
                         */
                        case UPDATE_DIALOG_UPDATEPROGRESS:
                            Log.i(TAG, "handleMessage: getMax: " + progressDialog.getMax());
                            Log.i(TAG, "handleMessage: prepare to setProgress: " + msg.arg2);
                            progressDialog.setProgress(msg.arg2);
                            break;
                    }
                    break;  // case UPDATE_DIALOG

                /**
                 * 线程：获取 Persons 列表
                 */
                case GET_PERSONS_LIST_TASK:
                    switch (msg.arg1) {
                        /**
                         * 获取成功
                         */
                        case GET_PERSONS_LIST_TASK_SUCCESS:
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            List<Person> persons = (List<Person>) msg.obj;
                            /**
                             * Sort persons by alphabetic spelling of names ( Ascending )
                             */
                            Collections.sort(persons, new Comparator<Person>() {
                                @Override
                                public int compare(Person o1, Person o2) {
                                    if (o1 == null || o2 == null) {
                                        return 0;
                                    }
                                    try{
                                        byte[] bufA = o1.getPinyin().getBytes();
                                        byte[] bufB = o2.getPinyin().getBytes();
                                        int compLength = Math.min(bufA.length, bufB.length);
                                        for (int i = 0; i < compLength ; i++) {
                                            if (bufA[i] < bufB[i])
                                                return -1;
                                            else if (bufA[i] > bufB[i])
                                                return 1;
                                        }
                                        return bufA.length - bufB.length;
                                    } catch (Exception e){
                                        return 0;
                                    }
                                }
                            });
                            intent.putExtra(XML_NODE_PERSON, (Serializable) persons);
                            startActivity(intent);
                            finish();
                            break;
                        /**
                         * 获取失败
                         */
                        case GET_PERSONS_LIST_TASK_FAIL:
                            Toast.makeText(SplashActivity.this,
                                    R.string.info_connecting_err, Toast.LENGTH_SHORT).show();
                            try {
                                sleep(2000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            finish();
                    }
            }
        }
    };
}
