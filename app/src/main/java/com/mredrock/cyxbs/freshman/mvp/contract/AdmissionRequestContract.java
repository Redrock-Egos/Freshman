package com.mredrock.cyxbs.freshman.mvp.contract;

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
        void setRv(Description description);
        void prepareAddData();
        void addData(Description.DescribeBean temp);
        void returnButton();
        void scrollToPos(int pos);
    }
}
