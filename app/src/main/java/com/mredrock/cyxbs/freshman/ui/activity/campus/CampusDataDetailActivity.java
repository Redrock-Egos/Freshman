package com.mredrock.cyxbs.freshman.ui.activity.campus;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.StatusBarUtils;

/*
  只是装载Fragment的容器，就不使用mvp了
 */

public class CampusDataDetailActivity extends AppCompatActivity {

    private ViewPager mVp;
    private String TAG = "CampusDataDetailActivity";
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_campus_data_datail);
        mVp = findViewById(R.id.vp_data_detail);
        toolbar = findViewById(R.id.tb_data_detail);
        DensityUtils.setTransparent(toolbar,this);
    }
}
