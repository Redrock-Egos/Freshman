package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.ui.adapter.MilitaryPhotoAdapter;
import com.mredrock.cyxbs.freshman.ui.adapter.MilitaryVideoAdapter;
import com.mredrock.cyxbs.freshman.ui.adapter.holder.MilitaryBannerHolder;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MilitaryShowPresenter extends BasePresenter<MilitaryShowContract.IMilitaryShowView> {
    private MilitaryShowContract.IMilitaryShowModel model;
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

                videoAdapter.addData(bean.getVideo());
                getView().getBanner().setPages(()
                        -> new MilitaryBannerHolder(),bean.getPicture())
                        .setPointViewVisible(true)
                        .startTurning(1000)
                        // TODO: 2018/8/11 拿到视觉图了再修改圆点
                        .setPageIndicator(new int[]{R.drawable.freshman_white_point,R.drawable.freshman_gray_point})
                        .setOnItemClickListener(position
                                -> ToastUtils.show(position+""))
                        .setManualPageable(true);
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
        videoAdapter = new MilitaryVideoAdapter(getView().getContext(),videos,new int[]{R.layout.freshman_item_military_video});
        getView().getVideoRV().setAdapter(videoAdapter);

        LinearLayoutManager ms1 = new LinearLayoutManager(getView().getContext());
        ms1.setOrientation(LinearLayoutManager.HORIZONTAL);//设置横向

        getView().getVideoRV().setLayoutManager(ms1);
    }


}
