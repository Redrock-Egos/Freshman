package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MienStu;

@SuppressLint("ValidFragment")
public class CquptMienStuFragment extends Fragment  {

    private MienStu.ArrayBean bean;
    private View parent;
    private ImageView img;
    private TextView tv;

    public CquptMienStuFragment(MienStu.ArrayBean bean) {
        this.bean = bean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_cqupt_mien_stu_detail,container,false);
        init();
        return parent;
    }

    private void init(){
        img = parent.findViewById(R.id.freshman_CyMien_detail_img);
        tv = parent.findViewById(R.id.freshman_CyMien_detail_desc);
        Glide.with(getContext()).load(bean.getPicture().get(0)).into(img);
        tv.setText(bean.getContent());
    }
}
