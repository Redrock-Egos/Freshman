package com.mredrock.cyxbs.freshman.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.mredrock.cyxbs.freshman.R;

/**
 * Created by Hosigus on 2018/7/20.
 */
public class DensityUtils {

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static void translucentStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public static void setStatusBarColor(Window window, int colorAgb) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorAgb);
        } else {
            translucentStatusBar(window);
        }
    }

    public static float dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    public static float px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display;
            Point size = new Point();
            if (wm != null) {
                display = wm.getDefaultDisplay();
                display.getSize(size);
            }
            screenHeight = size.y;
        }
        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display;
            Point size = new Point();
            if (wm != null) {
                display = wm.getDefaultDisplay();
                display.getSize(size);
            }
            screenWidth = size.x;
        }
        return screenWidth;
    }

    private static int getStatusBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 仅api大于等于19才能用
     * @param toolbar
     * @param context
     */
    public static void setTransparent(Toolbar toolbar,Context context){
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
           TypedArray array = context.obtainStyledAttributes(new int[]{R.attr.actionBarSize});
           //获取状态栏高度 并加上actionbarSize的高度
           float height = array.getDimension(0,0) + DensityUtils.getStatusBarHeight(context);
           array.recycle();
           ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
           layoutParams.height = (int) height;
           toolbar.setLayoutParams(layoutParams);
           //文字paddingTop为状态栏高度
           toolbar.setPadding(0,DensityUtils.getStatusBarHeight(context),0,0);
       }
    }
}