package com.mredrock.cyxbs.freshman.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryShowFragment;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryTipsFragment;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.StatusBarUtils;
import com.mredrock.cyxbs.freshman.utils.TabLayoutUtil;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

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

    }




    private void findById(){
        tabLayout = findViewById(R.id.freshman_military_tl);
        viewPager = findViewById(R.id.freshman_military_vp);
        btnBack = findViewById(R.id.freshman_military_iv_back);
        toolbar = findViewById(R.id.tb_military);
        tabLayout.post(() -> TabLayoutUtil.setIndicator(tabLayout,40,40));
        DensityUtils.setTransparent(toolbar,this);
        btnBack.setOnClickListener(v -> {
            MilitaryTrainingActivity.this.finish();
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

}
