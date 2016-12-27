package com.oidiotlin.classmanager.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.DatabaseManager;
import com.oidiotlin.classmanager.utils.MySqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class ManagerFragment extends ListFragment {
    private ListView listView;
    private SimpleAdapter adapter;

    private MySqliteHelper dbHelper;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager, null);
        listView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] listItem = { "a", "b", "c", "d", "e" };
        int[] bgItem = {
                R.color.bg_nameCard_0,
                R.color.bg_nameCard_1,
                R.color.bg_nameCard_2,
                R.color.bg_nameCard_3,
                R.color.bg_nameCard_4,
                R.color.bg_nameCard_5,
                R.color.bg_nameCard_6,
                R.color.bg_nameCard_7,
                R.color.bg_nameCard_8,
                R.color.bg_nameCard_9,
        };
        adapter = new SimpleAdapter(getActivity(), getData(listItem, bgItem),
                R.layout.list_view_item, new String[] {"text", "bgcolor"},
                new int[] {R.id.list_name, R.id.list_background}) ;
        setListAdapter(adapter);

        dbHelper = DatabaseManager.getInstance(this.getActivity());
    }

    private List<? extends Map<String, ?>> getData(String[] str, int[] bg) {
        //TODO 导入db到listItem中
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", str[i]);
            map.put("bgcolor", bg[(int)(i%10)]);
            list.add(map);
        }
        return list;
    }


}
