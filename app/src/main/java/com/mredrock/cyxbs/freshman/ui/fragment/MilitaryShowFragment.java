package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.mvp.contract.MilitaryShowContract;
import com.mredrock.cyxbs.freshman.mvp.model.MilitaryShowModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.MilitaryShowPresenter;
import com.mredrock.cyxbs.freshman.ui.widget.JCardView;

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

    private void initMvp(){
        presenter = new MilitaryShowPresenter(new MilitaryShowModel());
        presenter.attachView(this);
        presenter.start();
    }

    @Override
    public ViewPager getVideoVP() {
        return videoPager;
    }

    @Override
    public ViewPager getPhotoVP() {
        return viewPager;
    }
}

