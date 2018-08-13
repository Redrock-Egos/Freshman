package com.mredrock.cyxbs.freshman.mvp.presenter;

import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MilitaryShowPresenter extends BasePresenter<MilitaryShowContract.IMilitaryShowView> {
    private MilitaryShowContract.IMilitaryShowModel model;
    private List<String> photos;
    private List<String> videos;

    public static final  String TAG = "MilitaryShowPresenter";

    public MilitaryShowPresenter(MilitaryShowContract.IMilitaryShowModel model) {
        this.model = model;
    }

    public void start(){
        checkIsAttach();
        initArray();
        model.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                MilitaryShow bean = (MilitaryShow) o;
                // TODO: 2018/8/10 先模拟数据 之后测试
                getView().setData(bean);
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(msg);
            }
        });
    }

    private void initArray(){
        photos = new ArrayList<>();
        videos = new ArrayList<>();
    }
}
