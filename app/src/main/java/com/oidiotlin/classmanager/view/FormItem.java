package com.oidiotlin.classmanager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oidiotlin.classmanager.R;

import static com.oidiotlin.classmanager.activity.MainActivity.TAG;

/**
 * Created by OIdiot on 2017/2/24.
 * Project: ClassManager
 */

public class FormItem extends LinearLayout implements TextWatcher {
    private Boolean isTitleVisible;
    private String titleText;
    private int titleTextSize;
    private int titleTextColor;

    private int contentTextSize;
    private String contentHint;

    private int background;

    private TextView title;
    private EditText content;

    private LayoutParams titleParams;
    private LayoutParams contentParams;

    public FormItem(Context context) {
        this(context, null);
    }

    public FormItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FormItem);
        /**
         * 获取属性
         */
        isTitleVisible = typedArray.getBoolean(R.styleable.FormItem_title_visible, false);
        titleText = typedArray.getString(R.styleable.FormItem_title_text);
        titleTextColor = typedArray.getColor(R.styleable.FormItem_title_text_color, 0);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.FormItem_title_text_size, 0);

        contentTextSize = typedArray.getDimensionPixelSize(R.styleable.FormItem_content_text_size, 0);
        contentHint = typedArray.getString(R.styleable.FormItem_content_hint);

        background = typedArray.getColor(R.styleable.FormItem_background, 0);

        typedArray.recycle();

        title = new TextView(getContext());
        content = new EditText(getContext());

        /**
         * 加载属性
         */
        title.setAlpha(isTitleVisible ? 1 : 0);
        title.setTextSize(titleTextSize);
        title.setTextColor(titleTextColor);
        title.setText(titleText);

        content.setHint(contentHint);
        content.setFocusable(true);
        content.setInputType(InputType.TYPE_CLASS_TEXT);
        content.setFocusableInTouchMode(true);
        content.setTextSize(contentTextSize);
        content.setBackground(null);
        content.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "onFocusChange: " + hasFocus);
            }
        });

        this.setBackgroundColor(background);
        this.setPadding(0,0,0,0);
        titleParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(title, titleParams);
        contentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(content, contentParams);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s != null) {
            if (s.length() > 0)
                titleFadeIn();
            else
                titleFadeOut();
        } else
            titleFadeOut();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void titleFadeIn() {
        title.animate().alpha(1f).setDuration(500).start();
    }

    private void titleFadeOut() {
        title.animate().alpha(0f).setDuration(500).start();
    }

    private void titleToggle() {
        if (title.getAlpha() == 1)
            titleFadeOut();
        else
            titleFadeIn();
    }
}
