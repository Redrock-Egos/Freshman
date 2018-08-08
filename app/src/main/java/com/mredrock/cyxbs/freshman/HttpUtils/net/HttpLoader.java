package com.mredrock.cyxbs.freshman.HttpUtils.net;

import android.annotation.SuppressLint;

import com.mredrock.cyxbs.freshman.HttpUtils.callback.NetCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.Map;

/**
 * 用于加载网络
 */
public class HttpLoader {

    private static class HttpHolder{
        private final static HttpLoader m  = new HttpLoader();
    }

    public static HttpLoader getInstance(){
        return HttpHolder.m;
    }

    @SuppressLint("CheckResult")
    public void GET(String BASE_URL, String url, final NetCallback callback){
        ServiceManager.GetServiece(BASE_URL)
                .getObservable(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        callback.onResponse(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void POST(String BASE_URL, String url, Map<String,String> map, final NetCallback callback){
        ServiceManager.GetServiece(BASE_URL)
                .postObservable(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        callback.onResponse(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFailure(throwable);
                    }
                });
    }
}
