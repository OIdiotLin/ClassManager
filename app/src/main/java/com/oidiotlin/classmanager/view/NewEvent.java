package com.oidiotlin.classmanager.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oidiotlin.classmanager.R;

import java.util.Calendar;

import static android.view.View.MeasureSpec.makeMeasureSpec;
import static com.oidiotlin.classmanager.utils.system.Constant.DETAILS_ANIM_DURATION;

/**
 * Created by OIdiot on 2017/2/21.
 * Project: ClassManager
 */

public class NewEvent extends LinearLayout {

    public static final String TAG = "NewEvent";
    private Calendar calendar;
    private TextView title;
    private FormItem name;
    private FormItem place;
    private FormItem date;
    private FormItem time;
    private FormItem content;
    private FormItem participation;

    private View form;
    private View cancelButton;
    private View confirmButton;

    private int formHeight;

    public NewEvent(Context context) {
        this(context, null);
    }

    public NewEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_event, null);
        addView(view);

        title = (TextView) view.findViewById(R.id.new_event_title);
        name = (FormItem) view.findViewById(R.id.new_event_name);
        place = (FormItem) view.findViewById(R.id.new_event_place);
        date = (FormItem) view.findViewById(R.id.new_event_date);
        time = (FormItem) view.findViewById(R.id.new_event_time);
        content = (FormItem) view.findViewById(R.id.new_event_content);
        participation = (FormItem) view.findViewById(R.id.new_event_participation);

        form = view.findViewById(R.id.new_event_form);
        cancelButton = view.findViewById(R.id.button_cancel);
        confirmButton = view.findViewById(R.id.button_confirm);

        int height = makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int width = makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        form.measure(width, height);
        formHeight = form.getMeasuredHeight();
        Log.i(TAG, "NewEvent: formHeight = " + formHeight);
        form.getLayoutParams().height = 0;

        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleForm();
            }
        });

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelButtonClick();
            }
        });

        //this.requestFocus();
    }

    /**
     * 1. 检查表单完整性
     * 2. 提交表单至服务器
     */
    public void onConfirmButtonClick() {

    }

    /**
     * 隐藏此表单
     */
    public void onCancelButtonClick() {
        toggleForm();
    }

    public void toggleForm() {
        Log.i(TAG, "toggleForm: formHeight = " + formHeight);
        int currentHeight = form.getHeight();
        ValueAnimator animator;
        if (currentHeight == 0) {
            animator = ValueAnimator.ofInt(0, formHeight);
        } else if (currentHeight == formHeight) {
            animator = ValueAnimator.ofInt(formHeight, 0);
        } else return;
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                form.getLayoutParams().height = (int) animation.getAnimatedValue();
                form.requestLayout();
            }
        });
        animator.setDuration(DETAILS_ANIM_DURATION);
        animator.start();
    }
}

