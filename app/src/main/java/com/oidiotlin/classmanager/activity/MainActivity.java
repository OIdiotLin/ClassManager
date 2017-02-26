package com.oidiotlin.classmanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.fragment.EventFragment;
import com.oidiotlin.classmanager.fragment.ManagerFragment;

import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PERSON;


public class MainActivity extends FragmentActivity {
    public static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private RelativeLayout menuLayout;

    private ImageButton closeButton;
    private ImageButton managerButton;
    private ImageButton randPickButton;
    private ImageButton menuButton;
    private ImageButton helpButton;

    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuButton = (ImageButton) findViewById(R.id.toolbar_left_btn);
        helpButton = (ImageButton) findViewById(R.id.toolbar_right_btn);
        closeButton = (ImageButton) findViewById(R.id.menu_button_close);
        managerButton = (ImageButton) findViewById(R.id.menu_button_manager);
        randPickButton = (ImageButton) findViewById(R.id.menu_button_randpick);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuLayout = (RelativeLayout) findViewById(R.id.menu_layout);

        menuButton.setOnClickListener(new ToolBarButtonClickListener());
        helpButton.setOnClickListener(new ToolBarButtonClickListener());
        closeButton.setOnClickListener(new ToolBarButtonClickListener());
        managerButton.setOnClickListener(new ToolBarButtonClickListener());
        randPickButton.setOnClickListener(new ToolBarButtonClickListener());

        Fragment fragment = new ManagerFragment();
        bundle = new Bundle();
        bundle.putSerializable(XML_NODE_PERSON,
                getIntent().getSerializableExtra(XML_NODE_PERSON));
        fragment.setArguments(bundle);
        switchFragment(fragment, R.string.title_fragment_manager);
    }

    public class ToolBarButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Fragment fragment;
            switch (view.getId()) {
                /**
                 * tool buttons listener
                 */
                case R.id.toolbar_left_btn:
                    Log.i("ButtonListener", "onClick: menu button called.");
                    drawerLayout.openDrawer(menuLayout);
                    break;
                case R.id.toolbar_right_btn:
                    Log.i("ButtonListener", "onClick: help button called.");
                    Toast.makeText(MainActivity.this, R.string.help, Toast.LENGTH_SHORT).show();
                    break;
                /**
                 * menu buttons listener
                 */
                case R.id.menu_button_close:
                    Log.i("ButtonListener", "onClick: close button.");
                    drawerLayout.closeDrawer(menuLayout);
                    break;
                case R.id.menu_button_manager:
                    Log.i("ButtonListener", "onClick: manager button.");
                    fragment = new ManagerFragment();
                    fragment.setArguments(bundle);
                    switchFragment(fragment, R.string.title_fragment_manager);
                    break;
                case R.id.menu_button_randpick:
                    Log.i("ButtonListener", "onClick: rand pick button.");
                    fragment = new EventFragment();
                    switchFragment(fragment, R.string.title_fragment_event);
                default:
                    break;
            }
        }
    }

    /**
     * 切换到另一个 Fragment
     * @param myFrag 目标 Fragment
     * @param newTitle 切换后的新 Title
     */
    private void switchFragment (Fragment myFrag, int newTitle) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ((TextView)findViewById(R.id.toolbar_title)).setText(newTitle);
        ft.replace(R.id.frame_content, myFrag);
        ft.commit();
        drawerLayout.closeDrawer(menuLayout);
    }

}
