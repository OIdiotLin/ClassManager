package com.oidiotlin.classmanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.fragment.ManagerFragment;
import com.oidiotlin.classmanager.fragment.RandPickFragment;

//TODO 编写控件操作
public class MainActivity extends FragmentActivity {
    private DrawerLayout drawerLayout;
    private RelativeLayout menuLayout;
    private ImageButton menuButton;
    private ImageButton helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuButton = (ImageButton) findViewById(R.id.toolbar_left_btn);
        helpButton = (ImageButton) findViewById(R.id.toolbar_right_btn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuLayout = (RelativeLayout) findViewById(R.id.menu_layout);
        ListView listView = (ListView) findViewById(R.id.menu_list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_2));

        listView.setOnItemClickListener(new DrawerItemClickListener());
        menuButton.setOnClickListener(new ToolBarButtonClickListener());
        helpButton.setOnClickListener(new ToolBarButtonClickListener());
    }

    public class ToolBarButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
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
                case R.id.menu_button_manager:
                    Log.i("ButtonListener", "onClick: manager button.");
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = new ManagerFragment();
                    ((TextView)findViewById(R.id.toolbar_title)).setText(R.string.title_fragment_manager);
                    ft.replace(R.id.frame_content, fragment);
                    ft.commit();
                    drawerLayout.closeDrawer(menuLayout);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Menu 表点击事件
     */
    public class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.i("DrawerItemClickListener", "Click called!!" + i);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new ManagerFragment();
                    ((TextView)findViewById(R.id.toolbar_title)).setText(R.string.title_fragment_manager);
                    break;
                case 1:
                    fragment = new RandPickFragment();
                    ((TextView)findViewById(R.id.toolbar_title)).setText(R.string.title_fragment_randpick);
                    break;
                default:
                    break;
            }
            ft.replace(R.id.frame_content, fragment);
            ft.commit();
            drawerLayout.closeDrawer(menuLayout);
        }
    }


}
