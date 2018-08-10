package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;
import com.mredrock.cyxbs.freshman.ui.widget.ARHintDialog;

public class AdmissionRequestPresenter extends BasePresenter<AdmissionRequestContract.IAdmissionRequestView> {
    private AdmissionRequestContract.IAdmissionRequestModel mModel;
    private AdmissionRequestAdapter mAdapter;

    public AdmissionRequestPresenter(AdmissionRequestContract.IAdmissionRequestModel mModel ){
        this.mModel = mModel;

    }

    public void editRv(){
        mAdapter.deleteDatas();
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
            mAdapter.add(temp);
            getView().returnButton();
            getView().scrollToPos(mAdapter.getItemCount() - 1);
        }
    }

    public void changeMode(Boolean edit){
        mAdapter.changeData(edit);
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
                mAdapter = new AdmissionRequestAdapter(temp.getDescribe(), count -> {
                    String total = App.getContext().getResources().getString(R.string.freshmen_admission_delete);
                    if (count != 0)
                        total = App.getContext().getResources().getString(R.string.freshmen_admission_delete)+"("+count+")";
                    getView().setNum(total);
                });
                getView().setRv(mAdapter);
            }

            @Override
            public void failed(String msg) {
                getView().showError();
            }
        });
    }
}
