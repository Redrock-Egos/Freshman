package com.mredrock.cyxbs.freshman.ui.activity;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class App extends Application {
    private static Context appContext;
    private static RequestManager g;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        g = Glide.with(appContext);
    }

    public static Context getContext() {
        return appContext;
    }
    public static RequestManager g(){ return g; }
}
