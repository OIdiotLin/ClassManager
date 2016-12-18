package com.oidiotlin.classmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.oidiotlin.classmanager.R;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // allow changing window status
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_overview);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.common_toolbar);
    }
}
