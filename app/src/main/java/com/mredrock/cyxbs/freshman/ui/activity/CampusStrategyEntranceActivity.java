package com.mredrock.cyxbs.freshman.ui.activity;

/*
 只是简单的页面跳转Activity，没有使用mvp进行操作
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.EntranceAdapter;
import com.mredrock.cyxbs.freshman.utils.SPHelper;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusStrategyEntranceActivity extends AppCompatActivity implements View.OnClickListener {

    private EntranceAdapter simpleAdapter;
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_campus_strategy_entrance);
        ImageView back = findViewById(R.id.iv_entrance_back);
        mRv = findViewById(R.id.rv_entrance);

        back.setOnClickListener(this);
        initData();
    }

    private void initData(){
        Data mData = SPHelper.getBean("entrance",Data.class);
        List<SimpleData> datas = new ArrayList<>();
        if (mData.getData() == null){
            String[] names = {"学生食堂","学生寝室","周边美食","附近景点","校园环境","数据揭秘","附近银行","公交线路","快递收发"};
            int[] ids = {R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail
            ,R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail,R.drawable.freshman_icon_detail
                    ,R.drawable.freshman_icon_detail};
            for (int i = 0; i < names.length; i++) {
                SimpleData simpleData = new SimpleData();
                simpleData.setName(names[i]);
                simpleData.setId(ids[i]);
                datas.add(simpleData);
            }
            Data data = new Data();
            data.setData(datas);
            SPHelper.putBean("entrance",data);
        } else {
            datas = mData.getData();
        }
        simpleAdapter = new EntranceAdapter(datas, name -> {
            ToastUtils.show("点击了"+name);
            // TODO: 2018/8/11 页面跳转相关
        });
        GridLayoutManager manager = new GridLayoutManager(this,3);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(simpleAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

        public void setId(int id) {
            this.id = id;
        }
    }

}
