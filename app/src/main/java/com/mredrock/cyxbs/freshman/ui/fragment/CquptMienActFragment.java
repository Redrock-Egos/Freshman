package com.mredrock.cyxbs.freshman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.mvp.contract.CquptMienBaseContract;
import com.mredrock.cyxbs.freshman.mvp.model.CquptMienBaseModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.CquptMienActPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.CquptAdapter;

/**
 * 重邮风采第二个页面 展示大型活动
 */
public class CquptMienActFragment extends Fragment implements CquptMienBaseContract.ICquptMienActView  {
    private View parent;
    private RecyclerView recyclerView;
    private CquptMienActPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_cqupt_mien_act,container,false);
        initMvp();
        recyclerView = parent.findViewById(R.id.freshman_CyMien_act_rv);
        return parent;
    }

    private void initMvp(){
        presenter = new CquptMienActPresenter(new CquptMienBaseModel());
        presenter.attachView(this);
        presenter.start();
    }


    @Override
    public void setData(MienStu bean) {
        CquptAdapter cquptAdapter = new CquptAdapter(bean.getArray());
        recyclerView.setAdapter(cquptAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
    }
}
