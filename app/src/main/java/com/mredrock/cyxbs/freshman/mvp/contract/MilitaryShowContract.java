package com.mredrock.cyxbs.freshman.mvp.contract;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.cyxbs.freshman.bean.MilitaryShow;

public class MilitaryShowContract  {

    public interface IMilitaryShowModel extends BaseContract.ISomethingModel{
        void setItem(MilitaryShow bean, LoadCallBack callBack);
        void error(String str,LoadCallBack callBack);
    }

    public interface IMilitaryShowView extends BaseContract.ISomethingView{
        ViewPager getVideoVP();
        ViewPager getPhotoVP();
    }


}
