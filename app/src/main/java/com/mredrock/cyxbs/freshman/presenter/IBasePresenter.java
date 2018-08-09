package com.mredrock.cyxbs.freshman.presenter;


import com.mredrock.cyxbs.freshman.contract.BaseContract;

public interface IBasePresenter<V extends BaseContract.ISomethingView> {
    void attachView(V view);//绑定View
    void detachView();//解绑View
}
