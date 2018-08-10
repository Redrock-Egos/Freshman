package com.mredrock.cyxbs.freshman.mvp.model;

import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.mvp.contract.CquptMienBaseContract;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

import java.util.ArrayList;
import java.util.List;

public class CquptMienBaseModel implements CquptMienBaseContract.ICquptMienBaseModel{

    @Override
    public void loadData(LoadCallBack callBack) {
        HttpLoader.<MienStu>get(
                service -> service.getMienStu("学生组织","0","30")
                ,item -> setItem(item,callBack)
                ,error-> error(error.toString(),callBack)
        );
    }

    @Override
    public void setItem(MienStu bean, LoadCallBack callBack) {
        // TODO: 2018/8/10 模拟组织数据
        for (int i = 0; i < 10; i++) {
            MienStu.ArrayBean bean1 = new MienStu.ArrayBean();
            bean1.setName("组织"+i);
            bean1.setContent("这是内容"+i);
            bean1.setId(i);
            List<String> pictures = new ArrayList<>();
            pictures.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533900124472&di=be1abc11c6d2ed8b9d6a640b7cb7053f&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2Fdea862adbff072552df1f3158125c7d9abc12392.jpg");
            bean1.setPicture(pictures);
            bean.getArray().add(bean1);
        }
        callBack.succeed(bean);
    }

    @Override
    public void LoadAnotherData(LoadCallBack callBack) {
        HttpLoader.<MienStu>get(
                service -> service.getMienStu("大型活动","0","30")
                ,item -> setItem(item,callBack)
                ,error-> error(error.toString(),callBack)
        );
    }

    @Override
    public void setAnotherItem(MienStu bean, LoadCallBack callBack) {
        // TODO: 2018/8/10 模拟活动数据
        for (int i = 0; i < 10; i++) {
            MienStu.ArrayBean bean1 = new MienStu.ArrayBean();
            bean1.setName("组织"+i);
            bean1.setContent("这是内容"+i);
            bean1.setId(i);
            List<String> pictures = new ArrayList<>();
            pictures.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533900124472&di=be1abc11c6d2ed8b9d6a640b7cb7053f&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2Fdea862adbff072552df1f3158125c7d9abc12392.jpg");
            bean1.setPicture(pictures);
            bean.getArray().add(bean1);
        }
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }






}
