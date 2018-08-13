package com.mredrock.cyxbs.freshman.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.mvp.model.MilitaryShowModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.MilitaryShowPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.ViewPagerPhotoCardAdapter;
import com.mredrock.cyxbs.freshman.ui.adapter.ViewPagerVideoAdapter;
import com.mredrock.cyxbs.freshman.utils.banner.CardTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 军训风采展示的fragment
 */
public class MilitaryShowFragment extends Fragment implements MilitaryShowContract.IMilitaryShowView{
    private View parent;

    private MilitaryShowPresenter presenter;
    private ViewPager viewPager;
    private ViewPager videoPager;
    public static int mscreenWidth;
    public static int videoVPwidth;
    public List<String> photos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_military_show,container,false);
        findById();
        initMvp();
        return parent;
    }

    private void findById(){
        videoPager = parent.findViewById(R.id.freshman_military_show_video_vp);
        viewPager = parent.findViewById(R.id.freshman_military_show_photo_vp);
        getWidth2use();
    }

    private void initMvp(){
        presenter = new MilitaryShowPresenter(new MilitaryShowModel());
        presenter.attachView(this);
        presenter.start();
    }

    private void getWidth2use(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        int width = dm.widthPixels;
        float density = dm.density;
        int screenWidth = (int) (width / density);//获取屏幕宽度
        mscreenWidth = screenWidth;

        ViewGroup.LayoutParams layoutParams = videoPager.getLayoutParams();
        videoVPwidth = layoutParams.width;
    }

    @Override
    public void setData(MilitaryShow bean) {
        photos = new ArrayList<>();
        for (MilitaryShow.PictureBean bean1:bean.getPicture()) {
            photos.add(bean1.getUrl());
        }

        videoPager.setPageMargin((int) (MilitaryShowFragment.mscreenWidth/2.5));
        videoPager.setAdapter(new ViewPagerVideoAdapter(getView().getContext(),bean.getVideo()));
        videoPager.setOffscreenPageLimit(bean.getVideo().size());
        videoPager.setPageTransformer(true,new CardTransformer());

        viewPager.setAdapter(new ViewPagerPhotoCardAdapter(getView().getContext(),bean.getPicture(),photos));
        viewPager.setOffscreenPageLimit(bean.getPicture().size());
        viewPager.setPageMargin(MilitaryShowFragment.mscreenWidth/4);
        viewPager.setPageTransformer(true,new CardTransformer());
        viewPager.setCurrentItem(40000);


    }
}

