package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.oidiotlin.classmanager.utils.ScreenUtils;

/**
 * Created by OIdiot on 2016/12/19.
 * Project: ClassManager
 */

public class CustomSlider extends HorizontalScrollView {
    private int screenWidth;
    private int sliderPadding;
    private int sliderWidth;
    private int sliderHalfWidth;

    public CustomSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenWidth = ScreenUtils.getScreenWidth(context);
    }
}
