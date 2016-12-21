package com.oidiotlin.classmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.view.CustomSlider;


public class ManagerActivity extends Activity {
    private CustomSlider mMenu;
    private ImageButton menuBtn;
    private ImageButton helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mMenu = (CustomSlider) findViewById(R.id.slider_menu);
        menuBtn = (ImageButton) findViewById(R.id.toolbar_left_btn);
        helpBtn = (ImageButton) findViewById(R.id.toolbar_right_btn);
        menuBtn.setOnClickListener(new ButtonListener());
        helpBtn.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.toolbar_left_btn:
                    Log.i("ButtonListener", "onClick: called");
                    mMenu.toggle();
                    break;
                case R.id.toolbar_right_btn:
                    Log.i("ButtonListener", "onClick: right called");
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.help), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
