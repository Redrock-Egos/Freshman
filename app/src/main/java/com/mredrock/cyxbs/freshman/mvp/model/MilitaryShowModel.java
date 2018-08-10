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
        for (int i = 0; i < 10; i++) {
            bean.getPicture().add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533900124472&di=be1abc11c6d2ed8b9d6a640b7cb7053f&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2Fdea862adbff072552df1f3158125c7d9abc12392.jpg");
            bean.getVideo().add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533900124472&di=be1abc11c6d2ed8b9d6a640b7cb7053f&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Fface%2Fdea862adbff072552df1f3158125c7d9abc12392.jpg");
        }
        callBack.succeed(bean);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }


}
