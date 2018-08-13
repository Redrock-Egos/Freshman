package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.ui.activity.PhotoViewerActivity;
import com.mredrock.cyxbs.freshman.ui.activity.PhotoViewerActivityKt;

import java.util.List;

public class ViewPagerPhotoCardAdapter extends PagerAdapter {
    private Context context;
    private List<String> datas;

    public ViewPagerPhotoCardAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= datas.size();
        if (position < 0) {
            position = datas.size() + position;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.freshman_item_military_photo,null);
        TextView tv = view.findViewById(R.id.freshman_military_card_number);
        tv.setText("任务"+ (position + 1));
        RoundedImageView imageView = view.findViewById(R.id.freshman_military_card_photo);
        Glide.with(context).load(datas.get(position)).into(imageView);
        ViewParent parent = view.getParent();
        if(parent!=null){
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(view);
        }

        imageView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            ViewGroup.LayoutParams lp = tv.getLayoutParams();
            lp.height =imageView.getHeight()/4;
            tv.setLayoutParams(lp);
            imageView.setAdjustViewBounds(true);

        });

        int finalPosition = position;
        imageView.setOnClickListener(v -> {
            PhotoViewerActivityKt.start(context,datas, finalPosition);
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
