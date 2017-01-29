package com.oidiotlin.classmanager.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.Constant;
import com.oidiotlin.classmanager.utils.MySqliteHelper;
import com.oidiotlin.classmanager.utils.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class ManagerFragment extends ListFragment {
    public static final String TAG = "ManagerFragment";
    private ListView listView;
    private List<Person> lists;

    private MySqliteHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager, null);
        Log.i(TAG, "onCreateView: ");
        listView = (ListView) view.findViewById(android.R.id.list);
        dbHelper = new MySqliteHelper(getActivity());
        lists = new ArrayList<Person>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        lists.clear();
        Cursor cursor = db.rawQuery("select * from " + Constant.TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setName(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
                person.setGender(cursor.getString(cursor.getColumnIndex(Constant.GENDER)));
                person.setBirthday(cursor.getString(cursor.getColumnIndex(Constant.BIRTHDAY)));
                person.setStudentNumber(cursor.getString(cursor.getColumnIndex(Constant.STUDENT_NUMBER)));
                person.setNativeProvince(cursor.getString(cursor.getColumnIndex(Constant.NATIVE_PROVINCE)));
                person.setDormitory(cursor.getString(cursor.getColumnIndex(Constant.DORMITORY)));
                person.setPhoneNumber(cursor.getString(cursor.getColumnIndex(Constant.PHONE_NUMBER)));
                person.setPosition(cursor.getString(cursor.getColumnIndex(Constant.POSITION)));
                person.setParticipation(cursor.getInt(cursor.getColumnIndex(Constant.PARTICIPATION)));
                Log.i(TAG, "onCreateView: Person " + person.getName());
                lists.add(person);
            }
            /**
             * Sort lists by alphabetic spelling of names ( Ascending )
             */
            Collections.sort(lists, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    if (o1 == null || o2 == null) {
                        return 0;
                    }
                    try{
                        byte[] bufA = o1.getPinyin().getBytes();
                        byte[] bufB = o2.getPinyin().getBytes();
                        int compLength = Math.min(bufA.length, bufB.length);
                        for (int i = 0; i < compLength ; i++) {
                            if (bufA[i] < bufB[i])
                                return -1;
                            else if (bufA[i] > bufB[i])
                                return 1;
                        }
                        return bufA.length - bufB.length;
                    } catch (Exception e){
                        return 0;
                    }
                }
            });
            listView.setAdapter(new MyAdapter());
        }
        db.close();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
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
        */
    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                v = View.inflate(getContext(), R.layout.list_view_item, null);
            }else {
                v = convertView;
            }
            TextView name = (TextView) v.findViewById(R.id.item_name);
            ImageView gender = (ImageView) v.findViewById(R.id.item_gender);
            Person person = lists.get(position);
            name.setText(person.getName());
            gender.setImageResource(person.getGender().equals("M") ?
                    R.drawable.ic_male : R.drawable.ic_female);
            return v;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return lists.size();
        }
    }
    /*
    private List<? extends Map<String, ?>> getData(String[] str, int[] bg) {
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
    */
}
