package com.mredrock.cyxbs.freshman.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryTipsFragment;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryShowFragment;
import com.mredrock.cyxbs.freshman.utils.StatusBarUtils;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.jude.fitsystemwindowlayout.Utils.getStatusBarHeight;

/**
 * 军训特辑主界面，由于没有数据加载，只有简单的控件初始化，故不使用mvp
 */
public class MilitaryTrainingActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView btnBack;
    private Toolbar toolbar;
    private MyFragmentPagerAdapter adapter;

    private List<Fragment> fragments;
    private List<String> titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_military_training);
        findById();
        initView();
        StatusBarUtils.setImage(this);
    }




    private void findById(){
        tabLayout = findViewById(R.id.freshman_military_tl);
        viewPager = findViewById(R.id.freshman_military_vp);
        btnBack = findViewById(R.id.freshman_military_iv_back);
        toolbar = findViewById(R.id.tb_military);
        tabLayout.post(() -> {
            setIndicator(tabLayout,60,60);
        });
    }

    private void initView(){
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new MilitaryShowFragment());
        fragments.add(new MilitaryTipsFragment());
        titles.add("军训风采");
        titles.add("小贴士");
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(viewPager.getChildCount());
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.freshman_military_iv_back:
                ToastUtils.show("返回");
        }
    }


    /**
     * 反射修改tabLayout的下划线
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
