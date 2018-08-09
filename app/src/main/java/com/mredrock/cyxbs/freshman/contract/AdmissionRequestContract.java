package com.mredrock.cyxbs.freshman.contract;

import android.support.v7.widget.LinearLayoutManager;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;

public class AdmissionRequestContract {
    public interface IAdmissionRequestModel extends BaseContract.ISomethingModel{
        void setItem(Description description,LoadCallBack callBack);
        void error(String str,LoadCallBack callBack);
    }

    public interface IAdmissionRequestView extends BaseContract.ISomethingView{
        void showError();
        void setRv(AdmissionRequestAdapter mAdapter, LinearLayoutManager manager);
        void setNum(String info);
        void addData();
        void returnButton();
    }
}
