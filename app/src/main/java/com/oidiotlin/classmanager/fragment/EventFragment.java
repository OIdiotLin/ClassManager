package com.oidiotlin.classmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return inflater.inflate(R.layout.fragment_event, null);
    }
}
