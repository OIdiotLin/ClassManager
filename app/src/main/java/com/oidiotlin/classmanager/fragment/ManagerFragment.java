package com.oidiotlin.classmanager.fragment;

import android.animation.ValueAnimator;
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
import com.oidiotlin.classmanager.utils.parser.Person;
import com.oidiotlin.classmanager.utils.system.Constant;

import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PERSON;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class ManagerFragment extends ListFragment {
    public static final String TAG = "ManagerFragment";
    private ListView listView;
    private List<Person> persons;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager, null);
        Log.i(TAG, "onCreateView: ");
        listView = (ListView) view.findViewById(android.R.id.list);

        persons = (List<Person>)(getArguments().getSerializable(XML_NODE_PERSON));
        Log.i(TAG, "onCreateView: ManagerFragment: " + persons.toString());

        listView.setAdapter(new MyAdapter());
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Change the visibility of details by clicking the item
     * @param l The ListView where the click happened
     * @param v The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id The row id of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //final View details = l.getChildAt(position);
        final View details = v.findViewById(R.id.item_details);
        //Log.i(TAG, "onListItemClick: name = " + ((TextView)((View)details.getParent()).findViewById(R.id.item_name)).getText());
        int currentHeight = details.getHeight();
        ValueAnimator anim;
        if (currentHeight == Constant.DETAILS_HEIGHT) {
            anim = ValueAnimator.ofInt(Constant.DETAILS_HEIGHT, 0);
        }
        else if (currentHeight == 0) {
            anim = ValueAnimator.ofInt(0, Constant.DETAILS_HEIGHT);
        }
        else return;
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                details.getLayoutParams().height = (int) animation.getAnimatedValue();
                details.requestLayout();
            }
        });
        anim.setDuration(Constant.DETAILS_ANIM_DURATION);
        anim.start();
        super.onListItemClick(l, v, position, id);
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
                v = View.inflate(getContext(), R.layout.list_view_manager_item, null);
            }else {
                v = convertView;
            }
            /**
             * Access the Views
             */
            TextView name = (TextView) v.findViewById(R.id.item_name);
            ImageView gender = (ImageView) v.findViewById(R.id.item_gender);
            TextView phoneNumber = (TextView) v.findViewById(R.id.item_phone_number_text);
            TextView dormitory = (TextView) v.findViewById(R.id.item_dormitory_text);
            TextView nativeProvince = (TextView) v.findViewById(R.id.item_native_province_text);
            TextView birthday = (TextView) v.findViewById(R.id.item_birthday_text);
            TextView participation = (TextView) v.findViewById(R.id.item_participation_text);
            /**
             * Fill the View
             */
            Person person = persons.get(position);
            if(person.getName() != null)
                name.setText(person.getName());
            if(person.getGender() != null)
                gender.setImageResource(person.getGender().equals("M") ?
                        R.drawable.ic_male : R.drawable.ic_female);
            if(person.getPhoneNumber() != null)
                phoneNumber.setText(person.getPhoneNumber());
            if(person.getDormitory() != null)
                dormitory.setText(person.getDormitory());
            if(person.getNativeProvince() != null)
                nativeProvince.setText(person.getNativeProvince());
            if(person.getBirthday() != null)
                birthday.setText(person.getBirthday().substring(5));    // ignore year
            participation.setText(String.valueOf(person.getParticipation()));
            return v;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return persons.size();
        }
    }
}
