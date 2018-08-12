package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MilitaryShow;
import com.mredrock.cyxbs.freshman.ui.fragment.MilitaryShowFragment;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.List;

public class ViewPagerVideoAdapter extends PagerAdapter {
    private List<String> datas;
    private Context context;

    public ViewPagerVideoAdapter(Context context,List<String> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.freshman_item_military_video,null);

        TextView tv = view.findViewById(R.id.freshman_military_video_tv);
        tv.setText("视频"+ (position + 1));

        Button play = view.findViewById(R.id.freshman_military_video_play);
        play.setOnClickListener(v -> {
            ToastUtils.show("点击了第"+position+"个item");
        });
        RoundedImageView imageView = view.findViewById(R.id.freshman_military_video_iv);
        Glide.with(context).load(datas.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
