package com.mredrock.cyxbs.freshman.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

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
}