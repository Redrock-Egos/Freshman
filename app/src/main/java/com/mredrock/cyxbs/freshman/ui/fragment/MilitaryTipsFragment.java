package com.mredrock.cyxbs.freshman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryTipsContract;
import com.mredrock.cyxbs.freshman.mvp.model.MilitaryTipsModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.MilitaryTipsPresenter;

public class MilitaryTipsFragment extends Fragment implements MilitaryTipsContract.IMilitaryTipsView {
    private View parent;
    private TextView name;
    private TextView content;
    private MilitaryTipsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_military_tips,container,false);
        findById();
        initMvp();
        return parent;
    }

    private void findById(){
        name = parent.findViewById(R.id.freshman_military_tips_name);
        content = parent.findViewById(R.id.freshman_military_tips_content);
    }

    private void initMvp(){
        presenter = new MilitaryTipsPresenter(new MilitaryTipsModel());
        presenter.attachView(this);
        presenter.start();
    }

    @Override
    public TextView getNameTv() {
        return name;
    }

    @Override
    public TextView getContentTv() {
        return content;
    }
}
