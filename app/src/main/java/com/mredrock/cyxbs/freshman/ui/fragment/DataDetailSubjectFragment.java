package com.mredrock.cyxbs.freshman.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.SubjectProportion;
import com.mredrock.cyxbs.freshman.ui.widget.RectProcessView;

/*
 by Cynthia at 2018/8/16
 description : 
 */
public class DataDetailSubjectFragment extends Fragment {

    private int max;
    private int[] personNum;
    private String[] subjectName;

    private boolean rectFirst;

    private RectProcessView mRpv;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_fragment_data_detail_subject, container, false);
        rectFirst = true;
        initView(view);
        rootView = view;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView ==null)
            return;
        if (rectFirst && isVisibleToUser) {
            mRpv.setProcesses(personNum);
            mRpv.setSubject(subjectName);
            mRpv.setMax(max);
            rectFirst = false;
        }
    }

    private void initView(View view) {
        mRpv = view.findViewById(R.id.rpv_data);
        TextView title = view.findViewById(R.id.tv_data_subject);
        title.setText(getResources().getString(R.string.freshman_campus_data_title_subject));

    }

    public void loadSubject(SubjectProportion subject){
        int num = subject.getArray().size();
        max = 120;
        subjectName = new String[num];
        personNum = new int[num];
        for (int i = 0; i < num; i++) {
            subjectName[i] = subject.getArray().get(i).getSubject_name();
            personNum[i] = subject.getArray().get(i).getBelow_amount();
            if (personNum[i] > max){
                max = personNum[i];
            }
        }
        int temp = 120;
        while(temp < max){
           temp = temp + 30;
        }
        if (temp - max < temp / 6 / 3 && temp != 120)
            temp = temp + 30;
        max = temp;
    }
}
