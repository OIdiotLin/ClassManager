package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.oidiotlin.classmanager.R;
import com.oidiotlin.classmanager.utils.ScreenUtils;

/**
 * Created by OIdiot on 2016/12/19.
 * Project: ClassManager
 */

public class CustomSlider extends HorizontalScrollView {
    private int screenWidth;
    private int sliderPadding = 50;
    private int sliderWidth;
    private int sliderHalfWidth;
    private boolean once;

    private boolean isOpened;

    public CustomSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        screenWidth = ScreenUtils.getScreenWidth(context);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomSlider, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomSlider_rightPadding:
                    sliderPadding = a.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 50f,
                                    getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * Set a width
         */
        if(!once){
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
            ViewGroup content = (ViewGroup) wrapper.getChildAt(1);
            // set dp to px
            sliderPadding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, sliderPadding,
                    content.getResources().getDisplayMetrics());
            sliderWidth = screenWidth - sliderPadding;
            sliderHalfWidth = sliderWidth >> 1 ;
            menu.getLayoutParams().width = sliderWidth;
            content.getLayoutParams().width = screenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l,int t,int r,int b) {
        super.onLayout(changed, l, t, r, b);
        /**
         * unDisplay the slider
         */
        if(changed){
            this.scrollTo(sliderWidth, 0);
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
        case MotionEvent.ACTION_UP:
            int scrollX = getScrollX();
            if (scrollX > sliderHalfWidth)
                this.smoothScrollTo(sliderWidth, 0);
            else
                this.smoothScrollTo(0, 0);
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /*****************Operations********************/

    public void openMenu() {
        if (isOpened)
            return;
        this.smoothScrollTo(0, 0);
        isOpened = true;
    }

    public void closeMenu() {
        if (!isOpened)
            return;
        this.smoothScrollTo(sliderWidth, 0);
        isOpened = false;
    }

    public void toggle() {
        if (isOpened) {
            closeMenu();
        }else {
            openMenu();
        }
    }
}

