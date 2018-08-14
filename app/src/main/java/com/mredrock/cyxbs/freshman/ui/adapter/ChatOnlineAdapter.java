package com.mredrock.cyxbs.freshman.ui.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.ChatOnline;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.BaseHolder;
import com.mredrock.cyxbs.freshman.ui.adapter.frecyclerview.MultiLayoutBaseAdapter;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.List;

public class ChatOnlineAdapter<T> extends MultiLayoutBaseAdapter{
    List<ChatOnline.ArrayBean.Array1Bean> datas;

    public ChatOnlineAdapter(Context context, List<ChatOnline.ArrayBean.Array1Bean> data, int[] layoutIds) {
        super(context, data, layoutIds);
        this.datas = data;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public void onBinds(BaseHolder holder, Object o, int position, int viewType) {
        switch (viewType){
            case 0:
                TextView textView = holder.getView(R.id.freshman_chatonline_textView);
                textView.setText(datas.get(position).getName());
                holder.itemView.setOnClickListener(v -> {
                    ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    if(datas.get(position).getCode().length()>0){
                        cm.setText(datas.get(position).getCode());
//                        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
//                        getContext().startActivity(intent);

                        String urlQQ = "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=" + datas.get(position).getCode() + "&card_type=group&source=qrcode";
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlQQ)));
                        ToastUtils.show("开发小哥：已经复制了群号到您的剪切板上了呢！");
                    }
                });
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = DensityUtils.getScreenHeight(getContext())/16;
                holder.itemView.setLayoutParams(params);
                break;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void refreshData(ChatOnline bean){
       datas.clear();
        for (int i = 0; i < bean.getArray().size(); i++) {
            datas.addAll(bean.getArray().get(i).getArray1());
        }
       notifyDataSetChanged();
    }

    public void clearData(){
        datas.clear();
        notifyDataSetChanged();
    }
}
