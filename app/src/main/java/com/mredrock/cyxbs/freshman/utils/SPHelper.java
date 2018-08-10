package com.mredrock.cyxbs.freshman.utils;

import android.content.SharedPreferences;

import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.utils.kt.SpKt;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import io.reactivex.annotations.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class SPHelper {
    private static HashMap<String, SoftReference<SharedPreferences>> spList = new HashMap<>();

    public static SharedPreferences getSP(String name) {
        SoftReference<SharedPreferences> sp = spList.get(name);
        if (sp == null || sp.get() == null) {
            sp = new SoftReference<>(App.getContext().getSharedPreferences(name, MODE_PRIVATE));
            spList.put(name, sp);
        }
        return sp.get();
    }

    public static SharedPreferences getSP() {
        return getSP("FreshMan");
    }

    @Nullable
    public static <T> T getBean(String spName,String beanName,Class<T> clazz) {
        return SpKt.getBean(beanName, clazz, spName);
    }
    @Nullable
    public static <T> T getBean(String beanName,Class<T> clazz) {
        return SpKt.getBean(beanName, clazz);
    }

    public static <T> void putBean(String spName, String beanName, T bean) {
        SpKt.putBean(beanName, bean, spName);
    }

    public static <T> void putBean(String beanName, T bean) {
        SpKt.putBean(beanName, bean);
    }

}
