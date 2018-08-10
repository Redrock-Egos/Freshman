package com.mredrock.cyxbs.freshman.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.model.MilitaryShowModel;
import com.mredrock.cyxbs.freshman.model.MilitaryTipsModel;
import com.mredrock.cyxbs.freshman.presenter.MilitaryShowPresenter;
import com.mredrock.cyxbs.freshman.presenter.MilitaryTipsPresenter;

public class MilitaryShowFragment extends Fragment implements MilitaryShowContract.IMilitaryShowView{
    private View parent;
    private RecyclerView photo_rv;
    private RecyclerView video_rv;
    private MilitaryShowPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_military_show,container,false);
        findById();
        initMvp();
        return parent;
    }

    private void findById(){
        photo_rv = parent.findViewById(R.id.freshman_military_photo_rv);
        video_rv = parent.findViewById(R.id.freshman_military_video_rv);
    }

    private void initMvp(){
        presenter = new MilitaryShowPresenter(new MilitaryShowModel());
        presenter.attachView(this);
        presenter.start();
    }


    @Override
    public RecyclerView getPhotoRV() {
        return photo_rv;
    }

    @Override
    public RecyclerView getVideoRV() {
        return video_rv;
    }
}
