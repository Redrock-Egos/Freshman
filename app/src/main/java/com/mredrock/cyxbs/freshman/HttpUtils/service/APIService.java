package com.mredrock.cyxbs.freshman.HttpUtils.service;

import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.Map;


public interface APIService {

    @GET
    Observable<String> getObservable(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<String> postObservable(@Url String url, @FieldMap Map<String,String> map);

}
