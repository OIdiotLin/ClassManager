package com.oidiotlin.classmanager.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.activity.MainActivity;
import com.oidiotlin.classmanager.utils.parser.AppInfo;
import com.oidiotlin.classmanager.utils.network.UpdateAppTask;

/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public class UpdateDialog extends AlertDialog.Builder {
    public UpdateDialog(final Context context, final Handler handler, AppInfo info) {
        super(context);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        setCancelable(false);       // 使不能通过 back 键或点击空白来退出 dialog
        this.setIcon(R.drawable.ic_speaker);
        this.setTitle(R.string.update_dialog_title);
        this.setView(dialogView);
        TextView textView = (TextView) dialogView.findViewById(R.id.update_new_features);
        textView.setText(info.getNewFeatures());

        this.setPositiveButton(R.string.update_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,
                                R.string.update_start_hint, Toast.LENGTH_SHORT).show();
                        /**
                         * 开始下载并安装 apk
                         */
                        new Thread(new UpdateAppTask(context, handler)).start();
                    }
                });
        this.setNegativeButton(R.string.update_no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /**
                         * 拒绝更新则直接进入
                         * @see MainActivity
                         */
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });

    }
}
