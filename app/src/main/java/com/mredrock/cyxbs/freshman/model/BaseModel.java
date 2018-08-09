package com.mredrock.cyxbs.freshman.model;


import com.mredrock.cyxbs.freshman.contract.BaseContract;

public class BaseModel implements BaseContract.ISomethingModel {

    @Override
    public void loadData(LoadCallBack callBack) {
        //do something 然后用接口回调
    }
}
