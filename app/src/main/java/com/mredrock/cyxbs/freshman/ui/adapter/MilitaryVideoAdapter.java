package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.BaseHolder;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.MultiLayoutBaseAdapter;

import java.util.List;

public class MilitaryVideoAdapter<T> extends MultiLayoutBaseAdapter {

    public MilitaryVideoAdapter(Context context, List data, int[] layoutIds) {
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
                ImageView imageView = holder.getView(R.id.freshman_military_video_iv);
                Glide.with(getContext()).load(getData().get(position)).into(imageView);
                imageView.setOnClickListener(v -> {
                    // TODO: 2018/8/10 跳转url至浏览器
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
