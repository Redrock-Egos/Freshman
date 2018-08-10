package com.mredrock.cyxbs.freshman.mvp.contract;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;

public class CquptMienBaseContract {

    public interface ICquptMienBaseModel extends BaseContract.ISomethingModel{
        void setItem(MienStu bean, LoadCallBack callBack);
        void error(String str,LoadCallBack callBack);

        void LoadAnotherData(LoadCallBack callBack);
        void setAnotherItem(MienStu bean, LoadCallBack callBack);
    }

    public interface ICquptMienBaseView extends BaseContract.ISomethingView{
        TabLayout getTabLayout();
        ViewPager getViewPager();
        FragmentManager getIFragmentManager();
    }

    public interface ICquptMienActView extends BaseContract.ISomethingView{
        RecyclerView getRV();
    }
}
