package com.mredrock.cyxbs.freshman.model;

import android.util.Log;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.contract.BaseContract;
import com.mredrock.cyxbs.freshman.utils.net.Const;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

import java.util.Collections;
import java.util.List;

public class AdmissionRequestModel implements AdmissionRequestContract.IAdmissionRequestModel {

    private String TAG = "AdmissionRequest";


    @Override
    public void loadData(LoadCallBack callBack) {
//      先从缓存中读取，加入没有再从网络中获取
        HttpLoader.<Description>get(
                service -> service.getDescriptions(Const.INDEX_REQUIRED),
                item -> setItem(item,callBack),
                error -> error(error.toString(),callBack)
        );

    }

    @Override
    public void setItem(Description description, LoadCallBack callBack) {
        Log.i(TAG,description.toString());
        for (Description.DescribeBean m:description.getDescribe()) {
            m.setCheck(false);
            m.setDelete(false);
        }
        callBack.succeed(description);
    }

    @Override
    public void error(String str, LoadCallBack callBack) {
        callBack.failed(str);
        Log.e(TAG,str);
    }
}
