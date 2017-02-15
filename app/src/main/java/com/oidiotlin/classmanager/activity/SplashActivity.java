package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.network.AppInfo;
import com.oidiotlin.classmanager.utils.network.CheckVersionTask;
import com.oidiotlin.classmanager.utils.network.UpdateAppTask;
import com.oidiotlin.classmanager.view.UpdateDialog;

import static com.oidiotlin.classmanager.utils.system.AppUtils.isOnline;
import static com.oidiotlin.classmanager.utils.system.Constant.FORCED_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.NO_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.OPTIONAL_UPDATE;
import static com.oidiotlin.classmanager.utils.system.Constant.UPDATE_CLIENT;


public class SplashActivity extends Activity {

    private ImageView logo;
    private ImageView text;
    private TextView copyright;

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
                            new UpdateAppTask(SplashActivity.this, handler).run();
                            break;
                        /**
                         * 无更新，直接跳转到下一个 Activity
                         * @see  MainActivity
                         */
                        case NO_UPDATE:
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                            break;
                    }
            }
        }
    };
}
