package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.StrategyData;
import com.mredrock.cyxbs.freshman.ui.activity.App;
import com.mredrock.cyxbs.freshman.ui.activity.PhotoViewerActivity;
import com.mredrock.cyxbs.freshman.ui.activity.PhotoViewerActivityKt;
import com.mredrock.cyxbs.freshman.ui.activity.ReportingProcessActivity;
import com.mredrock.cyxbs.freshman.utils.net.Const;

import java.util.ArrayList;
import java.util.List;

/*
 by Cynthia at 2018/8/16
 description : 
 */
public class ReportingProcessAdapter extends RecyclerView.Adapter<ReportingProcessAdapter.ReportingProcessViewHolder> {

    private List<StrategyData.DetailData> list;
    private ClickItemListener listener;
    private Context content;

    public ReportingProcessAdapter(List<StrategyData.DetailData> report, ClickItemListener listener, Context context) {
        this.list = report;
        this.listener = listener;
        this.content = context;
    }

    @Override
    public ReportingProcessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.freshman_recycle_item_report, parent, false);
        return new ReportingProcessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportingProcessViewHolder holder, int position) {
        holder.loadData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<StrategyData.DetailData> getList() {
        return list;
    }

    class ReportingProcessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RoundedImageView real;
        private RoundedImageView map;
        private TextView title;
        private TextView step;
        private TextView context;
        private ImageView arrow;

        private String realStr;
        private String mapStr;

        ReportingProcessViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            real = view.findViewById(R.id.riv_report_real);
            map = view.findViewById(R.id.riv_report_map);
            title = view.findViewById(R.id.tv_report_location);
            step = view.findViewById(R.id.tv_report_step);
            context = view.findViewById(R.id.tv_report_context);
            arrow = view.findViewById(R.id.iv_report_arrow);

            real.setOnClickListener(this);
            map.setOnClickListener(this);
            context.setOnClickListener(this);
            arrow.setOnClickListener(this);
        }

        private void loadData(StrategyData.DetailData detailData) {
            title.setText(detailData.getName());
            String temp = "步骤" + detailData.getId();
            step.setText(temp);
            realStr = "http://47.106.33.112:8080/welcome2018" + detailData.getPicture().get(0);
            mapStr = "http://47.106.33.112:8080/welcome2018" + detailData.getPicture().get(1);
            Glide.with(App.getContext())
                    .load(realStr)
                    .asBitmap()
                    .thumbnail(0.2f)
                    .placeholder(R.drawable.freshman_preload_img)
                    .into(new BitmapImageViewTarget(real) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            real.setImageBitmap(resource);
                        }
                    });
            Glide.with(App.getContext())
                    .load(mapStr)
                    .asBitmap()
                    .thumbnail(0.2f)
                    .placeholder(R.drawable.freshman_preload_img)
                    .into(new BitmapImageViewTarget(map) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            map.setImageBitmap(resource);
                        }
                    });
            context.setText(detailData.getContent());
        }

        @Override
        public void onClick(View v) {
            List<String> url = new ArrayList<>();
            url.add(realStr);
            url.add(mapStr);
            switch (v.getId()) {
                case R.id.riv_report_map:
                    PhotoViewerActivityKt.start(content, url, 1);
                    break;
                case R.id.riv_report_real:
                    PhotoViewerActivityKt.start(content, url, 0);
                    break;
                case R.id.tv_report_context:
                case R.id.iv_report_arrow:
                    listener.isClick(getLayoutPosition());
                    break;
            }
        }
    }

    public interface ClickItemListener {
        void isClick(int pos);
    }
}
