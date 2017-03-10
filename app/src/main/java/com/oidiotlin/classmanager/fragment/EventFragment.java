package com.oidiotlin.classmanager.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.network.GetEventListTask;
import com.oidiotlin.classmanager.utils.parser.Event;

import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.GET_EVENTS_LIST_TASK;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_EVENTS_LIST_TASK_FAIL;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_EVENTS_LIST_TASK_SUCCESS;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class EventFragment extends Fragment {

    private static final String TAG = "EventFragment";
    private ListView listView;
    private List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, null);
        Log.i(TAG, "onCreateView: ");
        listView = (ListView) view.findViewById(R.id.events_list);

        new Thread(new GetEventListTask(getActivity(), handler)).start();

        return view;
    }

    public class EventAdapter extends BaseAdapter {
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
            //TODO 绑定数据
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                /** 获取 events 表 **/
                case GET_EVENTS_LIST_TASK:
                    switch (msg.arg1) {
                        /** 获取成功 **/
                        case GET_EVENTS_LIST_TASK_SUCCESS:
                            listView.setAdapter(new EventAdapter());
                            break;
                        /** 获取失败 **/
                        case GET_EVENTS_LIST_TASK_FAIL:
                            Toast.makeText(getActivity(),
                                    R.string.info_connecting_err, Toast.LENGTH_SHORT).show();

                            break;
                    }
                    break;
            }
        }
    };
}
