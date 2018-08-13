package com.mredrock.cyxbs.freshman.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mredrock.cyxbs.freshman.R;

/*
 by Cynthia at 2018/8/13
 description : 柱形进度条自定义View
 */
public class RectProcessView extends View{

    private int num;
    private int time;
    private float[] processes;
    private String[] colors;
    private float[] current;
    private Paint process;

    public RectProcessView(Context context) {
        this(context,null);
    }

    public RectProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RectProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        initPaints();
    }

    private void initAttr(AttributeSet attr){
        TypedArray typeArray = getContext().obtainStyledAttributes(attr, R.styleable.RectProcessView);
        CharSequence[] colors = typeArray.getTextArray(R.styleable.RectProcessView_processColor);
        CharSequence[] processes = typeArray.getTextArray(R.styleable.RectProcessView_processNumber);
        time = typeArray.getInteger(R.styleable.RectProcessView_time, 4);
        num = typeArray.getInt(R.styleable.RectProcessView_number, 1);
        typeArray.recycle();
        initData(colors, processes);
    }

    private void initData(CharSequence[] colors, CharSequence[] process) {
        this.colors = new String[num];
        processes = new float[num];
        for (int i = 0; i < num; i++) {
            this.colors[i] = String.valueOf(colors[i]);
            processes[i] = Float.parseFloat(String.valueOf(process[i]));
        }
        setAnim();
    }

    private void initPaints(){
        
    }

    private void setAnim() {
        ValueAnimator animator;
        current = new float[num];
        for (int i = 0; i < num; i++) {
            animator = ValueAnimator.ofFloat(0f, processes[i]);
            animator.setDuration(time * 1000);
            animator.setRepeatCount(0);
            animator.setInterpolator(new LinearInterpolator());
            final int finalI = i;
            animator.addUpdateListener(animation -> {
                current[finalI] = (float) animation.getAnimatedValue();
                invalidate();
            });
            animator.start();
        }
    }
}
