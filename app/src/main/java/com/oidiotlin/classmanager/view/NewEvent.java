package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by OIdiot on 2017/2/21.
 * Project: ClassManager
 */

public class NewEvent extends LinearLayout implements OnClickListener{

    public static final String TAG = "NewEvent";
    private Calendar calendar;

    public NewEvent(Context context) {
        super(context);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onClick(View v) {
        TextView lastTitle;
    }
}
