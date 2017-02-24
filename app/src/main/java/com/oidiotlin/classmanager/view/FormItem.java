package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.oidiotlin.classmanager.R;

/**
 * Created by OIdiot on 2017/2/24.
 * Project: ClassManager
 */

public class FormItem extends LinearLayout implements OnClickListener {
    private Boolean isTitleVisible;
    private int titleId;
    private int contentId;
    private int backgroundId;

    public FormItem(Context context) {
        this(context, null);
    }

    public FormItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FormItem);
        /**
         * Access title TextView
         */
        isTitleVisible = typedArray.getBoolean(R.styleable.FormItem_title_visible, false);
        titleId = typedArray.getResourceId(R.styleable.FormItem_title_text, -1);
        /**
         * Access contentId
         */
        // TODO 获取其他属性
        typedArray.recycle();
    }
}
