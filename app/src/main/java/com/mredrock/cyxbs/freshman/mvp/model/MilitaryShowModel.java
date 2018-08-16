package com.mredrock.cyxbs.freshman.mvp.model;

import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

public class MilitaryShowModel implements MilitaryShowContract.IMilitaryShowModel {


    @Override
    public void loadData(LoadCallBack callBack) {
        HttpLoader.<MilitaryShow>get(
                service -> service.getMilitaryShow(),
                item -> setItem(item,callBack),
                error -> error(error.toString(),callBack)
        );
    }

    @Override
    public void setItem(MilitaryShow bean, LoadCallBack callBack) {
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }


}
