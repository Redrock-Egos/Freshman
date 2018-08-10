package com.mredrock.cyxbs.freshman.mvp.presenter;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryTipsContract;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

public class MilitaryTipsPresenter extends BasePresenter<MilitaryTipsContract.IMilitaryTipsView> {
    private MilitaryTipsContract.IMilitaryTipsModel model;

    public MilitaryTipsPresenter(MilitaryTipsContract.IMilitaryTipsModel model) {
        this.model = model;
    }

    public void start(){
        checkIsAttach();
        model.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                Description description = (Description)o;
                getView().getContentTv().setText(description.getDescribe().get(0).getContent());
                getView().getNameTv().setText(description.getDescribe().get(0).getName());
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(msg);
            }
        });
    }
}
