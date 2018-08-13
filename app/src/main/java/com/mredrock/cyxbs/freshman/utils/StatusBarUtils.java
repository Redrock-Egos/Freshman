package com.mredrock.cyxbs.freshman.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 状态栏的实现封装类
 * 当顶部是一个图片，上移到状态栏
 *实现全透明的方法
 * 1.activity调用setImage方法
 * 2.xml里把父布局的颜色设置为和标题栏一样
 * 3.xml里把父布局设置为FitsSystemWindows=true
 * 4.若有需求，可以把下面的布局改会白色
 */
public class StatusBarUtils {
    /**
     * 当顶部是图片时，是图片显示到状态栏上
     *
     * @param activity
     */
    public static void setImage(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上，不设置透明状态栏，设置会有半透明阴影
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
}