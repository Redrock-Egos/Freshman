package com.mredrock.cyxbs.freshman.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.CustomVIewPager;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.CquptMienActFragment;
import com.mredrock.cyxbs.freshman.ui.fragment.CquptMienBaseFragment;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.TabLayoutUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 重邮风采主界面，由于没有数据加载，只有简单的控件初始化，故不使用mvp
 */

public class CquptMienActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView btnBack;
    private MyFragmentPagerAdapter adapter;
    private CustomVIewPager viewPager;
    private List<Fragment> fragments;
    private List<String> titles;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_cqupt_mien);
        findById();
        initVP();
    }

    private void findById(){
        viewPager = findViewById(R.id.freshman_CyMien_vp);
        btnBack = findViewById(R.id.freshman_CyMien_iv_back);
        tabLayout = findViewById(R.id.freshman_CyMien_tl);

        toolbar = findViewById(R.id.tb_CyMien);
        DensityUtils.setTransparent(toolbar,this);
        btnBack.setOnClickListener(this);
    }

    private void initVP(){
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new CquptMienBaseFragment(this));
        fragments.add(new CquptMienActFragment());
        titles.add("学生组织");
        titles.add("大型活动");
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(viewPager.getChildCount());
        viewPager.setScanScroll(false);//设置不能滑动
        tabLayout.setupWithViewPager(viewPager);
        TabLayoutUtil.setIndicator(tabLayout,50,50);

        ViewGroup.LayoutParams layoutParams = tabLayout.getLayoutParams();
        layoutParams.height = DensityUtils.getScreenHeight(this)/16;
        tabLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.freshman_CyMien_iv_back:
                finish();
                break;
        }
    }
}
