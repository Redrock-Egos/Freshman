package com.mredrock.cyxbs.freshman.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.SexProportion;
import com.mredrock.cyxbs.freshman.ui.widget.CircleProcessView;

/*
 by Cynthia at 2018/8/15
 description : 数据揭秘的承接数据的Fragment,复用
 0为男女比例，1为数据揭秘
 */
public class DataDetailSexFragment extends Fragment {

    private static String TAG = "DataDetailSexFragment";
    private String name;
    private float[] process;

    private CircleProcessView mCpv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_fragment_data_detail_sex, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mCpv = view.findViewById(R.id.cpv_data);
        mCpv.setProcess(process);
        TextView title = view.findViewById(R.id.tv_data_sex);
        String temp = name + getResources().getString(R.string.freshman_campus_data_detail_sex);
        title.setText(temp);
    }

    public void loadSex(SexProportion sexProportion) {
        float total = sexProportion.getFemale_amount() + sexProportion.getMale_amount();
        int manPro = (int) (sexProportion.getMale_amount() / total * 100);
        int womanPro = 100 - manPro;
        process = new float[]{womanPro, manPro};
    }


    public void setData(String name) {
        this.name = name;
    }
}
