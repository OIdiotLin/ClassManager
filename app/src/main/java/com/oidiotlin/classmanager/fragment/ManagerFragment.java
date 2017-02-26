package com.oidiotlin.classmanager.fragment;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ManagerFragment extends Fragment implements View.OnClickListener {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_name:
                toggleDetails(v);
                break;
            case R.id.person_call:
                callButtonOnClick(v);
                break;
        }
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
            TextView name = (TextView) v.findViewById(R.id.person_name);
            ImageView gender = (ImageView) v.findViewById(R.id.person_gender);
            TextView phoneNumber = (TextView) v.findViewById(R.id.person_phone_number_text);
            TextView dormitory = (TextView) v.findViewById(R.id.person_dormitory_text);
            TextView nativeProvince = (TextView) v.findViewById(R.id.person_native_province_text);
            TextView birthday = (TextView) v.findViewById(R.id.person_birthday_text);
            TextView participation = (TextView) v.findViewById(R.id.person_participation_text);
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

    public void toggleDetails(View v) {
        final View details = ((View) v.getParent()).findViewById(R.id.item_details);
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
    }

    /**
     * Show phone number in dial activity after click call button in item details
     * @param view the button clicked
     */
    public void callButtonOnClick(View view) {
        View parentView = (View) view.getParent();
        TextView phoneNumberView = (TextView) parentView.findViewById(R.id.person_phone_number_text);
        String number = (String) phoneNumberView.getText();
        Log.i(TAG, "callButtonOnClick: " + number);
        call(number);
    }
    private void call (String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
