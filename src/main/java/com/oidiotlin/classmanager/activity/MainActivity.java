package com.oidiotlin.classmanager.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import com.oidiotlin.classmanager.R;

//TODO 编写控件操作
public class MainActivity extends FragmentActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        mMenu = (CustomSlider) findViewById(R.id.slider_menu);
        menuBtn = (ImageButton) findViewById(R.id.toolbar_left_btn);
        helpBtn = (ImageButton) findViewById(R.id.toolbar_right_btn);
        menuBtn.setOnClickListener(new ButtonListener());
        helpBtn.setOnClickListener(new ButtonListener());
        */
    }
}
