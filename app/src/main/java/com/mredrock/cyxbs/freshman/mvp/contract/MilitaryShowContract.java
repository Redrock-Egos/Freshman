package com.mredrock.cyxbs.freshman.mvp.contract;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.cyxbs.freshman.bean.MilitaryShow;

import java.util.List;

public class MilitaryShowContract  {

    public interface IMilitaryShowModel extends BaseContract.ISomethingModel{
        void setItem(MilitaryShow bean, LoadCallBack callBack);
        void error(String str,LoadCallBack callBack);

    }

    public interface IMilitaryShowView extends BaseContract.ISomethingView{
        void setData(MilitaryShow bean);
    }


}
