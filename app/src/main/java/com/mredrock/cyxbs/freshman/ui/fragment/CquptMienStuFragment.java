package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.MienStu;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.net.Const;

@SuppressLint("ValidFragment")
public class CquptMienStuFragment extends Fragment {

    private MienStu.ArrayBean bean;
    private View parent;
    private RoundedImageView img;
    private TextView tv;
    private TextView name;
    private ImageView seeMore;
    private boolean isSeeMore = false;


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
        name = parent.findViewById(R.id.freshman_CyMien_detail_name);
        seeMore = parent.findViewById(R.id.freshman_CyMien_detail_seeMore);
        Glide.with(getContext()).load(Const.PHOTO_BASE_URL+bean.getPicture().get(0))
                .asBitmap()
                .placeholder(R.drawable.freshman_preload_img)
                .thumbnail(0.1f)
                .into(new BitmapImageViewTarget(img){
                    @Override
                    protected void setResource(Bitmap resource) {
                        img.setImageBitmap(resource);
                    }
                });
        tv.setText(bean.getContent());
        name.setText(bean.getName());
        tv.setLines(4);

        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.height = DensityUtils.getScreenHeight(getActivity())/4;
        img.setLayoutParams(layoutParams);

        seeMore.setOnClickListener(v -> {
            if(isSeeMore){
                tv.setMaxLines(4);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_more);
                isSeeMore = false;
            }else{
                tv.setMaxLines(500);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_simple);
                isSeeMore = true;
            }
        });
    }
}
