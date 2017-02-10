package com.oidiotlin.classmanager.view;

import android.app.AlertDialog;
import android.content.Context;

import com.oidiotlin.classmanager.utils.network.AppInfo;

/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public class UpdateDialog extends AlertDialog.Builder {

    public UpdateDialog(Context context, AppInfo info) {
        super(context);
    }
}
