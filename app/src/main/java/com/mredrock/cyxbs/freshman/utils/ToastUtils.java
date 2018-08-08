package com.mredrock.cyxbs.freshman.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hosigus on 2018/8/8.
 * 用全局Context设置setContext
 */

public class ToastUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static Toast mToast;

    private ToastUtils() {throw new UnsupportedOperationException("cannot be instantiated");}

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void show(String text) {
        show(text,Toast.LENGTH_SHORT);
    }

    @SuppressLint("ShowToast")
    public static void show(String text, int time) {
        if (mContext == null) {
            return;
        }

        if (time != Toast.LENGTH_SHORT && time != Toast.LENGTH_LONG) {
            return;
        }

        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, time);
        } else {
            mToast.setText(text);
        }

        mToast.show();
    }

}

