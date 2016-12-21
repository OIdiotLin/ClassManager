package com.oidiotlin.classmanager.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oidiotlin.classmanager.R;

/**
 * Created by OIdiot on 2016/12/21.
 * Project: ClassManager
 */

public class ContentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
