package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oidiotlin.classmanager.R;

/**
 * Created by OIdiot on 2016/12/18.
 * Project: ClassManager
 */

public class CustomToolBar extends LinearLayout {
    private Boolean isLeftBtnVisible;
    private Boolean isRightBtnVisible;
    private Boolean isTitleVisible;
    private int leftId;
    private int rightId;
    private String titleText;
    private int backgroundId;


    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
        /**
         * Access left button info
         */
        isLeftBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_left_btn_visible, false);
        leftId = typedArray.getResourceId(R.styleable.CustomToolBar_left_btn_src, -1);
        /**
         * Access right button info
         */
        isRightBtnVisible = typedArray.getBoolean(R.styleable.CustomToolBar_right_btn_visible, false);
        rightId = typedArray.getResourceId(R.styleable.CustomToolBar_right_btn_src, -1);
        /**
         * Access title info
         */
        isTitleVisible = typedArray.getBoolean(R.styleable.CustomToolBar_title_visible, false);
        if(typedArray.hasValue(R.styleable.CustomToolBar_title_text)) {
            titleText = typedArray.getString(R.styleable.CustomToolBar_title_text);
        }
        /**
         * Access background color
         */
        backgroundId = typedArray.getResourceId(R.styleable.CustomToolBar_bar_background, -1);
        typedArray.recycle();

        /**
         * Set new properties
         */
        View barLayoutView = View.inflate(getContext(), R.layout.common_toolbar, null);
        ImageButton leftBtn = (ImageButton)barLayoutView.findViewById(R.id.toolbar_left_btn);
        ImageButton rightBtn = (ImageButton)barLayoutView.findViewById(R.id.toolbar_right_btn);
        TextView title = (TextView)barLayoutView.findViewById(R.id.toolbar_title);
        RelativeLayout bar = (RelativeLayout)barLayoutView.findViewById(R.id.layout_toolbar_content);

        if(isLeftBtnVisible)    leftBtn.setVisibility(VISIBLE);
        if(isRightBtnVisible)   rightBtn.setVisibility(VISIBLE);
        if(isTitleVisible)      title.setVisibility(VISIBLE);

        if(leftId != -1)    leftBtn.setBackgroundResource(leftId);
        if(rightId != -1)   rightBtn.setBackgroundResource(rightId);
        if(backgroundId != -1)  bar.setBackgroundColor(getResources().getColor(R.color.bg_toolbar));

        addView(barLayoutView, 0);
    }
}
