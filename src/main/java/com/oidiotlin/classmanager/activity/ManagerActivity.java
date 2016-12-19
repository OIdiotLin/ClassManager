package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.oidiotlin.classmanager.R;


public class ManagerActivity extends Activity{
    private ImageButton menuBtn;
    private ImageButton helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        menuBtn = (ImageButton) findViewById(R.id.toolbar_left_btn);
        helpBtn = (ImageButton) findViewById(R.id.toolbar_right_btn);
        //menuBtn.setOnClickListener(this);
        //helpBtn.setOnClickListener(this);
    }
}
