package com.mredrock.cyxbs.freshman.mvp.presenter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.mvp.contract.BaseContract;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.ui.adapter.ViewPagerPhotoCardAdapter;
import com.mredrock.cyxbs.freshman.ui.adapter.ViewPagerVideoAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryShowFragment;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;
import com.mredrock.cyxbs.freshman.utils.banner.CardTransformer;

import java.util.ArrayList;
import java.util.List;

public class MilitaryShowPresenter extends BasePresenter<MilitaryShowContract.IMilitaryShowView> {
    private MilitaryShowContract.IMilitaryShowModel model;
    private List<String> photos;
    private List<String> videos;

    public static final  String TAG = "MilitaryShowPresenter";

    public MilitaryShowPresenter(MilitaryShowContract.IMilitaryShowModel model) {
        this.model = model;
    }

    public void start(){
        checkIsAttach();
        initArray();
        model.loadData(new BaseContract.ISomethingModel.LoadCallBack() {
            @Override
            public void succeed(Object o) {
                MilitaryShow bean = (MilitaryShow) o;
                // TODO: 2018/8/10 先模拟数据 之后测试

                // TODO: 2018/8/11 轮播图
                ViewPager viewPager = getView().getPhotoVP();
                viewPager.setAdapter(new ViewPagerPhotoCardAdapter(getView().getContext(),bean.getPicture()));
                viewPager.setOffscreenPageLimit(bean.getPicture().size());
                viewPager.setPageMargin(MilitaryShowFragment.mscreenWidth/4);
                viewPager.setPageTransformer(true,new CardTransformer());
                viewPager.setCurrentItem(40000);

                ViewPager viewPager1 = getView().getVideoVP();
                viewPager1.setPageMargin(MilitaryShowFragment.mscreenWidth/3);
                viewPager1.setAdapter(new ViewPagerVideoAdapter(getView().getContext(),bean.getVideo()));
                viewPager1.setOffscreenPageLimit(bean.getVideo().size());
                viewPager1.setPageTransformer(true,new CardTransformer());
            }

            @Override
            public void failed(String msg) {
                ToastUtils.show(msg);
            }
        });
    }

    private void initArray(){
        photos = new ArrayList<>();
        videos = new ArrayList<>();
    }
}
