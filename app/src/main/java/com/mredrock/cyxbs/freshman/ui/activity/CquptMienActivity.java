package com.mredrock.cyxbs.freshman.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.CustomVIewPager;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.CquptMienActFragment;
import com.mredrock.cyxbs.freshman.ui.fragment.CquptMienBaseFragment;
import com.mredrock.cyxbs.freshman.utils.StatusBarUtils;

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
    private Button ognization;
    private Button campaign;
    private Button open;
    private Button close;
    private Toolbar btnToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_cqupt_mien);
        findById();
        initVP();
    }

    private void findById(){
        viewPager = findViewById(R.id.freshman_CyMien_vp);
        ognization = findViewById(R.id.freshman_CyMien_btn_organization);
        campaign = findViewById(R.id.freshman_CyMien_btn_campaign);
        open = findViewById(R.id.freshman_CyMien_open);
        close = findViewById(R.id.freshman_CyMien_close);
        btnToolbar = findViewById(R.id.freshman_CyMien_tl);
        btnBack = findViewById(R.id.freshman_CyMien_iv_back);
        ognization.setOnClickListener(this);
        campaign.setOnClickListener(this);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        StatusBarUtils.setImage(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.freshman_CyMien_btn_organization:
                viewPager.setCurrentItem(0);
                showBtn();
                break;
            case R.id.freshman_CyMien_btn_campaign:
                viewPager.setCurrentItem(1);
                hideBtn();
                break;
            case R.id.freshman_CyMien_open:
                openTab();
                break;
            case R.id.freshman_CyMien_close:
                closeTab();
                break;
            case R.id.freshman_CyMien_iv_back:
                CquptMienActivity.this.finish();
                break;
        }
    }

    private void openTab(){
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        btnToolbar.setVisibility(View.VISIBLE);
        btnToolbar.startAnimation(mShowAction);
        open.setVisibility(View.GONE);
        close.setVisibility(View.VISIBLE);
    }

    private void closeTab(){
        btnToolbar.setVisibility(View.GONE);
        open.setVisibility(View.VISIBLE);
        close.setVisibility(View.GONE);
    }

    private void hideBtn(){
        close.setVisibility(View.GONE);
        open.setVisibility(View.GONE);
    }

    private void showBtn(){
        close.setVisibility(View.VISIBLE);
        open.setVisibility(View.VISIBLE );
    }

}
