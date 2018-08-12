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
        bean.getPicture().clear();
        bean.getVideo().clear();
        for (int i = 0; i < 5; i++) {
            bean.getPicture().add("https://b-ssl.duitang.com/uploads/item/201610/11/20161011152901_jCcUP.thumb.700_0.jpeg");
            bean.getPicture().add("https://b-ssl.duitang.com/uploads/item/201610/31/20161031081728_SiaNT.thumb.700_0.jpeg");
            bean.getVideo().add("https://b-ssl.duitang.com/uploads/item/201610/11/20161011152901_jCcUP.thumb.700_0.jpeg");
            bean.getVideo().add("https://b-ssl.duitang.com/uploads/item/201610/31/20161031081728_SiaNT.thumb.700_0.jpeg");
        }
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }


}
