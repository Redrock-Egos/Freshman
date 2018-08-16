package com.mredrock.cyxbs.freshman.ui.activity.campus;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.SexProportion;
import com.mredrock.cyxbs.freshman.bean.SubjectProportion;
import com.mredrock.cyxbs.freshman.mvp.contract.DataDetailContract;
import com.mredrock.cyxbs.freshman.mvp.model.DataDetailModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.DataDetailPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.MyFragmentPagerAdapter;
import com.mredrock.cyxbs.freshman.ui.fragment.DataDetailSexFragment;
import com.mredrock.cyxbs.freshman.ui.fragment.DataDetailSubjectFragment;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.TabLayoutUtil;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class CampusDataDetailActivity extends AppCompatActivity implements DataDetailContract.IDataDetailView {

    public static void start(String name, Context context) {
        Intent intent = new Intent(context, CampusDataDetailActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    private String name;
    private String TAG = "CampusDataDetailActivity";
    private ViewPager mVp;
    private TabLayout mTl;
    private DataDetailPresenter mPresenter;
    private DataDetailSexFragment sex;
    private DataDetailSubjectFragment subject;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_campus_data_datail);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        mVp = findViewById(R.id.vp_data_detail);
        Toolbar toolbar = findViewById(R.id.tb_data_detail);
        mTl = findViewById(R.id.tl_data);
        ImageView back = findViewById(R.id.iv_data_detail_back);

        back.setOnClickListener( v -> finish());

        DensityUtils.setTransparent(toolbar,this);

        initMVP();
    }

    private void initMVP(){
        sex = new DataDetailSexFragment();
        subject = new DataDetailSubjectFragment();
        sex.setData(name);
        mPresenter = new DataDetailPresenter(new DataDetailModel(name));
        mPresenter.attachView(this);
        mPresenter.start();
    }

    @Override
    public void showError(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void loadView(SexProportion sexProportion, SubjectProportion subject) {
        if (sexProportion != null)
            sex.loadSex(sexProportion);
        if (subject != null)
            this.subject.loadSubject(subject);
        initView();
    }

    private void initView(){
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        fragments.add(sex);
        fragments.add(subject);

        titles.add(getResources().getString(R.string.freshman_campus_data_detail_sex));
        titles.add(getResources().getString(R.string.freshman_campus_data_detail_difficult));
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        mVp.setAdapter(adapter);
        mVp.setOffscreenPageLimit(mVp.getChildCount());
        mTl.setupWithViewPager(mVp);
        TabLayoutUtil.setIndicator(mTl,40,40);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
