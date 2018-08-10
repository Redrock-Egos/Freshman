package com.mredrock.cyxbs.freshman.mvp.model;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryTipsContract;
import com.mredrock.cyxbs.freshman.utils.net.Const;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

public class MilitaryTipsModel implements MilitaryTipsContract.IMilitaryTipsModel {

    @Override
    public void loadData(LoadCallBack callBack) {
        // TODO: 2018/8/10 这里先用新生必备的测试
        HttpLoader.<Description>get(
                service -> service.getDescriptions(Const.INDEX_REQUIRED),
                item -> setItem(item,callBack),
                error -> error(error.toString(),callBack)
        );
    }


    @Override
    public void setItem(Description description, LoadCallBack callBack) {
        for (Description.DescribeBean bean:description.getDescribe()) {
            bean.setCheck(false);
            bean.setDelete(false);
        }
        callBack.succeed(description);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
    }
}
