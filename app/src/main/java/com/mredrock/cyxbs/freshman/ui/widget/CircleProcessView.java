package com.mredrock.cyxbs.freshman.ui.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mredrock.cyxbs.freshman.R;

/*
 by Cynthia at 2018/8/12
 description : 环形进度条
 */
public class CircleProcessView extends View {

    private Paint circlePaint;
    private Paint textPaint;
    private Paint processPaint;
    private float[] centerLocation;
    private float[] processes;
    private String[] colors;
    private int time;
    private int num;
    private float[] process = new float[4];
    private float[] current = new float[4];
    private RectF rectF = new RectF();
    private Rect mText = new Rect();

    public CircleProcessView(Context context) {
        this(context, null);
    }

    public CircleProcessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        initPaints();
    }

    private void initAttr(AttributeSet attributeSet) {
        TypedArray typeArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircleProcessView);
        CharSequence[] colors = typeArray.getTextArray(R.styleable.CircleProcessView_colors);
        CharSequence[] processes = typeArray.getTextArray(R.styleable.CircleProcessView_processes);
        time = typeArray.getInteger(R.styleable.CircleProcessView_animatorTime, 4);
        num = typeArray.getInt(R.styleable.CircleProcessView_circleNumber, 1);
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

    private void initPaints() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);

        processPaint = new Paint();
        processPaint.setAntiAlias(true);
        processPaint.setStyle(Paint.Style.STROKE);
        processPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dp2px(12));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int length = 0;
        if (widthMode == heightMode && widthMode == MeasureSpec.AT_MOST) {
            length = Math.min(heightSize, widthSize);
            length = Math.min(length, dp2px(200));
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            length = heightSize;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        } else {
            length = Math.min(heightSize, widthSize);
            length = Math.min(length, dp2px(200));
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);
        }
        setPosition(length);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        int y = getHeight() / 2;


        for (int i = 0; i < num; i++) {
            circlePaint.setColor(Color.parseColor(colors[i]));
            circlePaint.setAlpha(120);
            canvas.drawCircle(x, y, centerLocation[i], circlePaint);

            processPaint.setColor(Color.parseColor("#ffffff"));
            processPaint.setAlpha(100);

            rectF.set(x - centerLocation[i],x - centerLocation[i],x + centerLocation[i],x + centerLocation[i]);

            @SuppressLint("DefaultLocale")
            String mProcess = String.format("%.1f", process[i]);
            String text = mProcess + "%";
            textPaint.getTextBounds(text, 0, String.valueOf(text).length(), mText);
            textPaint.setColor(Color.parseColor(colors[i]));
            canvas.drawArc(rectF, -90, current[i] * 360 / 100, false, processPaint);
            canvas.drawText(text, (float) x, (float) x - centerLocation[i] + dp2px(3), textPaint);

        }
    }

    private void setPosition(int width) {
        float useSize = width * 0.5f * 0.75f;
        float paddingSize = width * 0.5f * 0.25f;
        float everySize = useSize / num;
        circlePaint.setStrokeWidth(everySize / 3 * 2);
        processPaint.setStrokeWidth((everySize / 3 * 2) - dp2px(2));
        centerLocation = new float[num];
        for (int i = 0; i < num; i++) {
            centerLocation[i] = paddingSize + (i + 0.5f) * everySize;
        }

    }

    private void setAnim() {
        ValueAnimator animator;
        for (int i = 0; i < num; i++) {
            animator = ValueAnimator.ofFloat(0f, processes[i]);
            animator.setDuration(time * 1000);
            animator.setRepeatCount(0);
            animator.setInterpolator(new LinearInterpolator());
            final int finalI = i;
            animator.addUpdateListener(animation -> {
                current[finalI] = (float) animation.getAnimatedValue();
                process[finalI] = (float) animation.getAnimatedValue();
                invalidate();
            });
            animator.start();
        }
    }

    private int dp2px(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
