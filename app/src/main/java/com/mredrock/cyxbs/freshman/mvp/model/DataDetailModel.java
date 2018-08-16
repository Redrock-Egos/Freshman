package com.mredrock.cyxbs.freshman.mvp.model;

import android.util.Log;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.SexProportion;
import com.mredrock.cyxbs.freshman.bean.SubjectProportion;
import com.mredrock.cyxbs.freshman.mvp.contract.DataDetailContract;
import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.utils.SPHelper;
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader;

/*
 by Cynthia at 2018/8/15
 description :
  */
public class DataDetailModel implements DataDetailContract.IDataDetailModel {

    private String name;
    private String TAG = "DataDetailModel";

    public DataDetailModel(String name){
        this.name = name;
    }

    @Override
    public void loadData(LoadCallBack callBack) {
        SexProportion sexProportion = SPHelper.getBean("sex",name,SexProportion.class);
        SubjectProportion subjectProportion = SPHelper.getBean("subject",name,SubjectProportion.class);
        if (sexProportion == null){
            HttpLoader.<SexProportion>get(
                    service -> service.getSexProportion(name),
                    item -> setSex(item,callBack),
                    error -> error(error.toString(),callBack));
        } else {
            setSex(sexProportion,callBack);
        }
        if (subjectProportion == null){
            HttpLoader.<SubjectProportion>get(
                    service -> service.getSubjectProportion(name),
                    item -> setSubject(item,callBack),
                    error -> error(error.toString(),callBack));
        } else {
            setSubject(subjectProportion,callBack);
        }
    }

    private void setSex(SexProportion sex,LoadCallBack callBack){
        callBack.succeed(sex);
        SPHelper.putBean("sex",name,sex);
    }

    private void setSubject(SubjectProportion subject, LoadCallBack loadCallBack){
        loadCallBack.succeed(subject);
        SPHelper.putBean("subject",name,subject);
    }

    private void error(String error,LoadCallBack callBack){
        Log.i(TAG,error);
        callBack.failed(App.getContext().getResources().getString(R.string.freshman_error_soft));
    }
}
