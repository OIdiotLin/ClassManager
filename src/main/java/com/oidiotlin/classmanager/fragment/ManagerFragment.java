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
        String[] itemName = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"
        };
        int[] itemGender = {
                R.drawable.ic_male, R.drawable.ic_female
        };
        adapter = new SimpleAdapter(getActivity(), getData(itemName, itemGender),
                R.layout.list_view_item, new String[] {"text", "bgcolor"},
                new int[] {R.id.item_name, R.id.item_gender}) ;
        setListAdapter(adapter);

        dbHelper = DatabaseManager.getInstance(this.getActivity());
    }

    private List<? extends Map<String, ?>> getData(String[] str, int[] bg) {
        //TODO 导入db到listItem中
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int len = str.length;
        for (int i = 0; i < len; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", str[i]);
            //map.put("bgcolor", bg[(int)(i%10)]);
            map.put("bgcolor", bg[1]);
            list.add(map);
        }
        return list;
    }


}
