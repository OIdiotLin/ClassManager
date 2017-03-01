package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.oidiotlin.classmanager.R;

import java.util.Calendar;

/**
 * Created by OIdiot on 2017/2/21.
 * Project: ClassManager
 */

public class NewEvent extends LinearLayout {

    public static final String TAG = "NewEvent";
    private Calendar calendar;
    private FormItem name;
    private FormItem place;
    private FormItem date;
    private FormItem time;
    private FormItem content;


    public NewEvent(Context context) {
        this(context, null);
    }

    public NewEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_event, null);
        addView(view);
    }
}

