package com.oidiotlin.classmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.parser.Event;

import java.util.List;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class EventFragment extends ListFragment {

    private static final String TAG = "EventFragment";
    private ListView listView;
    private List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, null);
        Log.i(TAG, "onCreateView: ");
        listView = (ListView) view.findViewById(R.id.events_list);
        return view;
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
                v = View.inflate(getContext(), R.layout.list_view_events_item, null);
            } else {
                v = convertView;
            }
            /**
             * Access the Views
             */
            return v;
        }

        @Override
        public int getCount() {
            if (events != null)
                return events.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }
}
