package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.content.Context;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;
import com.mredrock.cyxbs.freshman.ui.widget.ARHintDialog;
import com.mredrock.cyxbs.freshman.utils.SPHelper;

public class AdmissionRequestPresenter extends BasePresenter<AdmissionRequestContract.IAdmissionRequestView> {
    private AdmissionRequestContract.IAdmissionRequestModel mModel;

    public AdmissionRequestPresenter(AdmissionRequestContract.IAdmissionRequestModel mModel ){
        this.mModel = mModel;

    }
    public void addItem(String str){
        if (str.equals("")){
            getView().returnButton();
        } else {
            Description.DescribeBean temp = new Description.DescribeBean();
            temp.setName(str);
            temp.setDelete(false);
            temp.setCheck(false);
            temp.setContent("");
            temp.setProperty("用户自定义");
            getView().addData(temp);
        }
    }

    public void showDialog(Context context){
        ARHintDialog mDialog = new ARHintDialog(context);
        mDialog.show();
    }

    public void start(){
        checkIsAttach();//检查是否绑定
        mModel.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                Description temp = (Description) o;
                getView().setRv(temp);
            }

            @Override
            public void failed(String msg) {
                getView().showError();
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
