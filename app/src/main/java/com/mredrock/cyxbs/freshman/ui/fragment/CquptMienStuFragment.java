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
import com.mredrock.cyxbs.freshman.ui.widget.JCardView;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.net.Const;

@SuppressLint("ValidFragment")
public class CquptMienStuFragment extends Fragment {

    private MienStu.ArrayBean bean;
    private View parent;
    private RoundedImageView img;
    private TextView tv;
    private ImageView seeMore;
    private boolean isSeeMore = false;


    public void setBean(MienStu.ArrayBean bean) {
        this.bean = bean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_cqupt_mien_stu_detail, container, false);
        init();
        return parent;
    }

    private void init() {
        img = parent.findViewById(R.id.freshman_CyMien_detail_img);
        tv = parent.findViewById(R.id.freshman_CyMien_detail_desc);
        TextView name = parent.findViewById(R.id.freshman_CyMien_detail_name);
        seeMore = parent.findViewById(R.id.freshman_CyMien_detail_seeMore);
        JCardView jCardView = parent.findViewById(R.id.freshman_CyMien_detail_jv);
        jCardView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        if (bean.getName().equals("校学生会") ||//这几个图片不能使用centerCrop
                bean.getName().equals("重庆邮电大学青年志愿者协会") ||
                bean.getName().equals("社团联合会") ||
                bean.getName().equals("学生科技联合会")) {
            Glide.with(getContext()).load(Const.IMG_BASE_URL + bean.getPicture().get(0))
                    .asBitmap()
                    .placeholder(R.drawable.freshman_preload_img)
                    .thumbnail(0.1f)
                    .into(new BitmapImageViewTarget(img) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            img.setImageBitmap(resource);
                        }
                    });
        } else {
            Glide.with(getContext()).load(Const.IMG_BASE_URL + bean.getPicture().get(0))
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.freshman_preload_img)
                    .thumbnail(0.1f)
                    .into(new BitmapImageViewTarget(img) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            img.setImageBitmap(resource);
                        }
                    });
        }
        tv.setText(bean.getContent());
        name.setText(bean.getName());
        tv.setLines(6);

        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.height = DensityUtils.getScreenHeight(getActivity()) / 4;
        img.setLayoutParams(layoutParams);

        tv.setOnClickListener(v -> {
            if (isSeeMore) {
                tv.setMaxLines(6);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_more);
                isSeeMore = false;
            } else {
                tv.setMaxLines(500);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_simple);
                isSeeMore = true;
            }
        });

        seeMore.setOnClickListener(v -> {
            if (isSeeMore) {
                tv.setMaxLines(6);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_more);
                isSeeMore = false;
            } else {
                tv.setMaxLines(500);
                seeMore.setBackgroundResource(R.drawable.freshman_icon_report_simple);
                isSeeMore = true;
            }
        });

    }

}
