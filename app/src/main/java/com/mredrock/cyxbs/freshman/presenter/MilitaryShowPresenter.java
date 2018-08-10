package com.mredrock.cyxbs.freshman.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.contract.BaseContract;
import com.mredrock.cyxbs.freshman.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.ui.adapter.MilitaryPhotoAdapter;
import com.mredrock.cyxbs.freshman.ui.adapter.MilitaryVideoAdapter;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MilitaryShowPresenter extends BasePresenter<MilitaryShowContract.IMilitaryShowView> {
    private MilitaryShowContract.IMilitaryShowModel model;
    private MilitaryPhotoAdapter photoAdapter;
    private MilitaryVideoAdapter videoAdapter;
    private List<String> photos;
    private List<String> videos;

    public MilitaryShowPresenter(MilitaryShowContract.IMilitaryShowModel model) {
        this.model = model;
    }

    public void start(){
        checkIsAttach();
        setRV();
        model.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                MilitaryShow bean = (MilitaryShow) o;
                // TODO: 2018/8/10 先模拟数据 之后测试
                photoAdapter.addData(bean.getPicture());
                videoAdapter.addData(bean.getVideo());
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(msg);
            }
        });
    }

    private void setRV(){
        photos = new ArrayList<>();
        videos = new ArrayList<>();
        photoAdapter = new MilitaryPhotoAdapter(getView().getContext(),photos,new int[]{R.layout.freshman_item_military_photo});
        videoAdapter = new MilitaryVideoAdapter(getView().getContext(),videos,new int[]{R.layout.freshman_item_military_video});
        getView().getPhotoRV().setAdapter(photoAdapter);
        getView().getVideoRV().setAdapter(videoAdapter);

        LinearLayoutManager ms = new LinearLayoutManager(getView().getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向
        LinearLayoutManager ms1 = new LinearLayoutManager(getView().getContext());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向

        getView().getPhotoRV().setLayoutManager(ms);
        getView().getVideoRV().setLayoutManager(ms1);
    }


}
