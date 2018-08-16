package com.mredrock.cyxbs.freshman.ui.activity.campus;

/*
 只是简单的页面跳转Activity，没有使用mvp进行操作
 */


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.activity.strategy.SameStrategyActivityKt;
import com.mredrock.cyxbs.freshman.ui.adapter.EntranceAdapter;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.net.Const;

import java.util.ArrayList;
import java.util.List;

public class CampusStrategyEntranceActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private Toolbar toolbar;

    private String TAG = "CampusStrategyEntranceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_campus_strategy_entrance);
        ImageView back = findViewById(R.id.iv_entrance_back);
        mRv = findViewById(R.id.rv_entrance);
        toolbar = findViewById(R.id.tb_entrance);

        back.setOnClickListener(this);

        DensityUtils.setTransparent(toolbar,this);
        initData();
    }

    private void initData() {
        List<SimpleData> datas = new ArrayList<>();
        String[] names = {Const.INDEX_CANTEEN, Const.INDEX_DORMITORY, Const.INDEX_CATE,
                Const.INDEX_SCENIC, Const.INDEX_ENVIRONMENT, Const.INDEX_REVEAL,
                Const.INDEX_BANK, Const.INDEX_BUS, Const.INDEX_EXPRESS};
        @IdRes
        int[] ids = {R.drawable.freshman_icon_canteen, R.drawable.freshman_icon_dormitory, R.drawable.freshman_icon_cate,
                R.drawable.freshman_icon_scenic, R.drawable.freshman_icon_environment, R.drawable.freshman_icon_data,
                R.drawable.freshman_icon_bank, R.drawable.freshman_icon_bus, R.drawable.freshman_icon_express};
        for (int i = 0; i < names.length; i++) {
            SimpleData simpleData = new SimpleData();
            simpleData.setName(names[i]);
            simpleData.setId(ids[i]);
            datas.add(simpleData);
        }
        EntranceAdapter simpleAdapter = new EntranceAdapter(datas, name -> {
            SameStrategyActivityKt.createStrategyActivity(CampusStrategyEntranceActivity.this, name);
        });
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(simpleAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_entrance_back:
                finish();
                break;
        }
    }

    private class Data{

        private List<SimpleData> mData;

        public List<SimpleData> getData() {
            return mData;
        }

        public void setData(List<SimpleData> mData) {
            this.mData = mData;
        }
    }

    public class SimpleData{
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(@IdRes int id) {
            this.id = id;
        }
    }

}
