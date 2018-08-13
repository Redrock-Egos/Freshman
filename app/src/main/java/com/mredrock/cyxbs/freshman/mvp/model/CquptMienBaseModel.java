package com.mredrock.cyxbs.freshman.mvp.model;

import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.mvp.contract.CquptMienBaseContract;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

public class CquptMienBaseModel implements CquptMienBaseContract.ICquptMienBaseModel{

    @Override
    public void loadData(LoadCallBack callBack) {
        HttpLoader.<MienStu>get(
                service -> service.getMienStu("学生组织","1","30")
                ,item -> setItem(item,callBack)
                ,error-> error(error.toString(),callBack)
        );
    }

    @Override
    public void setItem(MienStu bean, LoadCallBack callBack) {
        callBack.succeed(bean);
    }

    @Override
    public void LoadAnotherData(LoadCallBack callBack) {
        HttpLoader.<MienStu>get(
                service -> service.getMienStu("大型活动","1","30")
                ,item -> setItem(item,callBack)
                ,error-> error(error.toString(),callBack)
        );
    }

    @Override
    public void setAnotherItem(MienStu bean, LoadCallBack callBack) {
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }






}
