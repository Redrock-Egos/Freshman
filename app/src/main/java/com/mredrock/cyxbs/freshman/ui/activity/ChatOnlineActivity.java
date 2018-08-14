package com.mredrock.cyxbs.freshman.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.ChatOnlineFragment;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.TabLayoutUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 线上交流
 */
public class ChatOnlineActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment>  fragments;
    private List<String> titles;
    private MyFragmentPagerAdapter adapter;
    private Toolbar toolbar;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_chat_online);
        find();
        initVP();
    }

    private void find(){
        viewPager = findViewById(R.id.freshman_chatOnline_vp);
        tabLayout = findViewById(R.id.freshman_chatOnline_tl);
        toolbar = findViewById(R.id.tb_chatOnline);
        back = findViewById(R.id.freshman_chatOnline_iv_back);
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void initVP(){
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        DensityUtils.setTransparent(toolbar,this);
        fragments.add(new ChatOnlineFragment(this,"学校群"));
        fragments.add(new ChatOnlineFragment(this,"老乡群"));
        titles.add("学院群");
        titles.add("老乡群");
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(viewPager.getChildCount());
        tabLayout.setupWithViewPager(viewPager);
        TabLayoutUtil.setIndicator(tabLayout,40,40);
    }


}
