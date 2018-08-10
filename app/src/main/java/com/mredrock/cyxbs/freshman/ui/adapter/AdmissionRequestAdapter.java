package com.mredrock.cyxbs.freshman.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.ui.activity.App;

import java.util.List;

/**
 * 只是因为懒。。就没有把Adapter和ViewHolder解耦了
 */

public class AdmissionRequestAdapter extends RecyclerView.Adapter<AdmissionRequestAdapter.AdmissionRequestViewHolder> {

    private List<Description.DescribeBean> mDataList;
    private Boolean isEdit;
    private OnDeleteDataListener mListener;
    private int deleteNum = 0;

    public AdmissionRequestAdapter(List<Description.DescribeBean> mDataList,OnDeleteDataListener mListener){
        this.mDataList = mDataList;
        isEdit = false;
        this.mListener = mListener;
    }

    @Override
    public AdmissionRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(App.getContext()).inflate(R.layout.freshman_recycle_item_admission,parent,false);
        return new AdmissionRequestViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(AdmissionRequestViewHolder holder, int position) {
        holder.initData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void changeData(Boolean isEdit){
        this.isEdit = isEdit;
        deleteNum = 0;
        notifyDataSetChanged();
    }

    public void add(Description.DescribeBean bean){
        mDataList.add(bean);
        notifyItemInserted(mDataList.size());
    }

    public void deleteDatas(){
        for (int i = 0; i < mDataList.size(); i++) {
            if (mDataList.get(i).isDelete())
                mDataList.remove(i);
        }
        notifyDataSetChanged();
    }

    class AdmissionRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView item;
        private ImageView more;
        private ImageView delete;
        private TextView title;
        private TextView description;

        private int close = -1;
        private int status;
        private boolean check;

        AdmissionRequestViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.iv_admission_check);
            title = itemView.findViewById(R.id.tv_admission_count);
            delete = itemView.findViewById(R.id.iv_admission_delete);
            more = itemView.findViewById(R.id.iv_admission_more);
            description = itemView.findViewById(R.id.tv_admission_description);

            item.setOnClickListener(this);
            more.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        void initData(Description.DescribeBean mData){
            status = close;
            title.setText(mData.getName());
            int colorId = mData.isCheck()? R.color.finish_black:R.color.title_black;
            title.setTextColor(App.getContext().getResources().getColor(colorId));
            description.setVisibility(View.GONE);
            description.setText(mData.getContent());
            if (!isEdit){
                check = false;
                delete.setVisibility(View.GONE);
                item.setVisibility(View.VISIBLE);
            } else {
                item.setVisibility(View.INVISIBLE);
                if(mData.getProperty().equals("必需")){
                    delete.setVisibility(View.GONE);
                } else {
                    delete.setVisibility(View.VISIBLE);
                }
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_admission_check:
//                todo 上浮
                    if (!check) {
                        check = true;
                        item.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_pressed));
                        title.setTextColor(App.getContext().getResources().getColor(R.color.finish_black));
                        mDataList.get(getLayoutPosition()).setCheck(true);
                    } else {
                        check = false;
                        item.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_normal));
                        title.setTextColor(App.getContext().getResources().getColor(R.color.title_black));
                        mDataList.get(getLayoutPosition()).setCheck(false);
                    }
                    break;
                case R.id.iv_admission_more:
                    if (status == close){
                        more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_simple));
                        description.setVisibility(View.VISIBLE);
                        status = -2;
                    } else {
                        more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_more));
                        description.setVisibility(View.GONE);
                        status = close;
                    }
                    break;
                case R.id.iv_admission_delete:
                    if (mDataList.get(getLayoutPosition()).isDelete()){
                        mDataList.get(getLayoutPosition()).setDelete(false);
                        delete.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_delete_normal));
                        deleteNum = deleteNum - 1;
                        mListener.getTotalNum(deleteNum);
                    } else {
                        mDataList.get(getLayoutPosition()).setDelete(true);
                        delete.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_delete_pressed));
                        deleteNum = deleteNum + 1;
                        mListener.getTotalNum(deleteNum);
                    }
            }
        }
    }

    public interface OnDeleteDataListener{
        void getTotalNum(int count);
    }
}
