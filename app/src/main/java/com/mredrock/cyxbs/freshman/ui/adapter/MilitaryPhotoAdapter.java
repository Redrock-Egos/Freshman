package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.BaseHolder;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.MultiLayoutBaseAdapter;

import java.util.List;

public class MilitaryPhotoAdapter<T> extends MultiLayoutBaseAdapter {
    
    public MilitaryPhotoAdapter(Context context, List<String> data, int[] layoutIds) {
        super(context, data, layoutIds);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public void onBinds(BaseHolder holder, Object o, int position, int viewType) {
        switch (viewType){
            case 0:
                ImageView imageView = holder.getView(R.id.freshman_military_photo_iv);
                // TODO: 2018/8/10 加入占位图
                Glide.with(getContext()).load(getData().get(position)).into(imageView);
                imageView.setOnClickListener(v -> {
                    // TODO: 2018/8/10 加入photoView
                });
                break;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void addData(List<String> newData){
        getData().addAll(newData);
        notifyDataSetChanged();
    }
}
