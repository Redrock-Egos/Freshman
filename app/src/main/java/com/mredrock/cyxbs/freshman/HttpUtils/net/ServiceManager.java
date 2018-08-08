package com.mredrock.cyxbs.freshman.HttpUtils.net;

import com.mredrock.cyxbs.freshman.HttpUtils.service.APIService;

/**
 * 用于优化多次代理service
 */
public class ServiceManager {
    private static boolean isFirstLoad = true;
    private static APIService service;

    public static APIService GetServiece(String BASE_URL){
        if(isFirstLoad){
            service = RetrofitManager.GetRetrofit(BASE_URL).create(APIService.class);
            isFirstLoad = false;
            System.out.println(service);
            return service;
        }else {
            System.out.println(service);
            return service;
        }
    }
}
