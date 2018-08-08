package com.mredrock.cyxbs.freshman.HttpUtils.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 用于配置retrofit
 */
public class RetrofitManager {
    private static Retrofit retrofit;

    public static Retrofit GetRetrofit(String BASE_URL){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
         return retrofit;
    }


}
