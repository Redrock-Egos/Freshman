package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.support.v4.app.Fragment;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.CquptMienBaseContract;
import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.CquptMienStuFragment;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class CquptMienBasePresenter extends BasePresenter<CquptMienBaseContract.ICquptMienBaseView> {
    private CquptMienBaseContract.ICquptMienBaseModel model;
    private MyFragmentPagerAdapter adapter;

    public CquptMienBasePresenter(CquptMienBaseContract.ICquptMienBaseModel model) {
        this.model = model;
    }

    public void start(){
        model.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                MienStu stu = (MienStu) o;
                if(stu!=null){
                    List<Fragment> fragments = new ArrayList<>();
                    List<String> titles = new ArrayList<>();

                    for (int i = 0; i < stu.getArray().size(); i++) {
                        stu.getArray().get(i).getPicture().set(0,"http://f4.topitme.com/4/c3/a4/11254861942a7a4c34o.jpg");
                        CquptMienStuFragment fragment = new CquptMienStuFragment(stu.getArray().get(i));
                        fragments.add(fragment);
                        titles.add(stu.getArray().get(i).getName());
                    }
                    getView().setData(fragments,titles);
                }
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(App.getContext().getResources().getString(R.string.freshman_error_soft));
            }
        });

    }






}
