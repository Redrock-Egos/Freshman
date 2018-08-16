package com.mredrock.cyxbs.freshman.mvp.presenter;

import com.mredrock.cyxbs.freshman.bean.SexProportion;
import com.mredrock.cyxbs.freshman.bean.SubjectProportion;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.DataDetailContract;
import com.mredrock.cyxbs.freshman.mvp.model.DataDetailModel;

/*
 by Cynthia at 2018/8/15
 description : 
 */
public class DataDetailPresenter extends BasePresenter<DataDetailContract.IDataDetailView> {

    private DataDetailModel dataDetailModel;
    private SexProportion sex;
    private SubjectProportion subject;

    public DataDetailPresenter(DataDetailModel model){
        this.dataDetailModel = model;
    }

    public void start(){
        checkIsAttach();//检查是否绑定
        dataDetailModel.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                if (o instanceof SexProportion){
                    sex = (SexProportion)o;
                } else {
                    subject = (SubjectProportion) o;
                }
                getView().loadView(sex,subject);
            }

            @Override
            public void failed(String msg) {
                getView().showError(msg);
            }
        });
    }
}
