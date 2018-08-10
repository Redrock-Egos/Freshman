package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.BaseHolder;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.MultiLayoutBaseAdapter;

import java.util.List;

public class CquptMienActAdapter<T> extends MultiLayoutBaseAdapter {
    List<MienStu.ArrayBean> beans;

    public CquptMienActAdapter(Context context, List<MienStu.ArrayBean> data, int[] layoutIds) {
        super(context, data, layoutIds);
        this.beans = data;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public void onBinds(BaseHolder holder, Object o, int position, int viewType) {
        switch (viewType){
            case 0:
                ImageView photo = holder.getView(R.id.freshman_CyMien_act_img);
                TextView content = holder.getView(R.id.freshman_CyMien_act_tv);
                // TODO: 2018/8/10 加入占位符
                Glide.with(getContext()).load(beans.get(position).getPicture().get(0)).into(photo);
                content.setText(beans.get(position).getContent());
                break;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
