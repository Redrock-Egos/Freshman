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
        // TODO: 2018/8/13 模拟数据 之后删掉 
        for (int i = 0; i < bean.getPicture().size(); i++) {
            bean.getPicture().get(i).setUrl("https://b-ssl.duitang.com/uploads/item/201610/11/20161011152901_jCcUP.thumb.700_0.jpeg");
        }
        for (int i = 0; i < bean.getVideo().size(); i++) {
            bean.getVideo().get(i).getVideo_pic().setUrl("https://b-ssl.duitang.com/uploads/item/201610/11/20161011152901_jCcUP.thumb.700_0.jpeg");
        }
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }


}
