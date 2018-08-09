package com.mredrock.cyxbs.freshman.utils.net;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.bean.Entity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit 的精髓，正常写Retrofit的接口就好
 */
public interface APIService {
    /**
     * 接口1,获得某一类实体的总数和实体的名称（用实体名称来获取文字描述和图片）
     * @param index index的值应为Consts类中INDEX_开头的常量
     * @return Entity实体类
     * @see Const
     * @see Entity
     */
    @GET("data/describe/getamount")
    Observable<Entity> getEntityName(@Query("index") String index);

    @GET("data/get/describe")
    Observable<Description> getDescriptions(@Query("index") String index);
}
