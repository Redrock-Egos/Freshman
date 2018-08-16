package com.mredrock.cyxbs.freshman.mvp.contract;

import com.mredrock.cyxbs.freshman.bean.SexProportion;
import com.mredrock.cyxbs.freshman.bean.SubjectProportion;

/*
 by Cynthia at 2018/8/15
 description : 
 */
public class DataDetailContract {
    public interface IDataDetailModel extends BaseContract.ISomethingModel{

    }

    public interface IDataDetailView extends BaseContract.ISomethingView{
        void showError(String msg);
        void loadView(SexProportion sexProportion , SubjectProportion subject);
    }
}
