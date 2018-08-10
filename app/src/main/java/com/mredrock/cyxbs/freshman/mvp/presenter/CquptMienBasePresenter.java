package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.CquptMienBaseContract;
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
                    initFragment(stu);
                }
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(msg);
            }
        });

    }




    private void initFragment(MienStu stu){
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        ViewPager vp = getView().getViewPager();
        TabLayout layout = getView().getTabLayout();

        for (int i = 0; i < stu.getArray().size(); i++) {
            CquptMienStuFragment fragment = new CquptMienStuFragment(stu.getArray().get(i));
            fragments.add(fragment);
            titles.add(stu.getArray().get(i).getName());
        }
        adapter = new MyFragmentPagerAdapter(getView().getIFragmentManager(),fragments,titles);
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(vp.getChildCount());
        layout.setupWithViewPager(vp);
    }

}
