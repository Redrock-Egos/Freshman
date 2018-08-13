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
 * 也是因为懒，不想写动画了
 */

public class AdmissionRequestAdapter extends RecyclerView.Adapter<AdmissionRequestAdapter.AdmissionRequestViewHolder> {

    private List<Description.DescribeBean> mDataList;
    private Boolean isEdit;
    private OnDeleteDataListener mListener;
    private int deleteNum = 0;

    public AdmissionRequestAdapter(List<Description.DescribeBean> mDataList, OnDeleteDataListener mListener){
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
//      删除时候从大到小，不然会删不干净...
        for (int i = mDataList.size() - 1; i > 0; i--) {
            if (mDataList.get(i).isDelete()){
                mDataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public Description getDatas(){
        Description temp = new Description();
        temp.setIndex("入学必备");
        temp.setDescribe(mDataList);
        return temp;
    }


    public class AdmissionRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView item;
        private ImageView more;
        private ImageView delete;
        private TextView title;
        private TextView description;

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
            title.setText(mData.getName());
            int colorId = mData.isCheck()? R.color.freshmen_finish_black:R.color.freshmen_title_black;
            int drawableIdCheck = mData.isCheck()? R.drawable.freshman_check_pressed:R.drawable.freshman_check_normal;
            int drawableIdDelete = mData.isDelete()? R.drawable.freshman_check_delete_pressed:R.drawable.freshman_check_delete_normal;
            title.setTextColor(App.getContext().getResources().getColor(colorId));
            item.setImageDrawable(App.getContext().getResources().getDrawable(drawableIdCheck));
            delete.setImageDrawable(App.getContext().getResources().getDrawable(drawableIdDelete));
            if (mData.isOpen()){
                description.setVisibility(View.VISIBLE);
                description.setText(mData.getContent());
                more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_simple));
            } else {
                description.setVisibility(View.GONE);
                more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_more));
            }
            if (mData.getProperty().equals("用户自定义")){
                more.setVisibility(View.GONE);
            }
            if (!isEdit){
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

        private void from2 (int from, int to){
            Description.DescribeBean temp = mDataList.get(from);
            mDataList.remove(from);
            mDataList.add(to,temp);
            notifyItemRemoved(from);
            notifyItemInserted(to);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_admission_check:
                    if (!mDataList.get(getLayoutPosition()).isCheck()) {
                        item.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_pressed));
                        title.setTextColor(App.getContext().getResources().getColor(R.color.freshmen_finish_black));
                        mDataList.get(getLayoutPosition()).setCheck(true);
                        from2(getLayoutPosition(),0);
                    } else {
                        item.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_check_normal));
                        title.setTextColor(App.getContext().getResources().getColor(R.color.freshmen_title_black));
                        mDataList.get(getLayoutPosition()).setCheck(false);
                        from2(getLayoutPosition(),getItemCount() - 1);
                    }
                    break;
                case R.id.iv_admission_more:
                    if (!mDataList.get(getLayoutPosition()).isOpen()){
                        more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_simple));
                        notifyItemChanged(getLayoutPosition(),0);
                        mDataList.get(getLayoutPosition()).setOpen(true);
                    } else {
                        more.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.freshman_icon_see_more));
                        notifyItemChanged(getLayoutPosition(),0);
                        mDataList.get(getLayoutPosition()).setOpen(false);
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
