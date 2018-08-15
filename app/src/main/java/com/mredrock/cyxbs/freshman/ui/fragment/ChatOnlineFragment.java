package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.ChatOnline;
import com.mredrock.cyxbs.freshman.mvp.contract.ChatOnlineContract;
import com.mredrock.cyxbs.freshman.mvp.model.ChatOnlineModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.ChatOnlinePresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.ChatOnlineAdapter;
import com.mredrock.cyxbs.freshman.ui.widget.JCardView;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ChatOnlineFragment extends Fragment implements ChatOnlineContract.IChatOnlineView{
    private Context context;
    private String kind;
    private View parent;
    private String key;

    private EditText editText;
    private RecyclerView recyclerView;
    private int screenHeight;
    private ChatOnlinePresenter presenter;
    private List<ChatOnline.ArrayBean> datas;
    private ChatOnlineAdapter adapter;
    private ImageView sreach_img;
    private JCardView jCardView;
    private ImageView view;

    public ChatOnlineFragment(Context context, String kind) {
        this.context = context;
        this.kind = kind;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_fragment_chatonline,container,false);
        initP();
        init();
        setET();
        return parent;
    }

    private void initP(){
        presenter = new ChatOnlinePresenter(new ChatOnlineModel());
        presenter.attachView(this);
    }

    private void init(){
        editText = parent.findViewById(R.id.freshman_chatonline_et);
        recyclerView = parent.findViewById(R.id.freshman_chatonline_rv);
        sreach_img = parent.findViewById(R.id.freshman_chat_search);
        jCardView = parent.findViewById(R.id.freshman_chatonline_jc);
        view = parent.findViewById(R.id.freshman_chatonline_v);
        datas = new ArrayList<>();
    }
    private void setET(){
        adapter = new ChatOnlineAdapter(getContext(),datas,new int[]{R.layout.freshman_item_chatonline_lv});
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        screenHeight = DensityUtils.getScreenHeight(getContext());

        ViewGroup.LayoutParams lp = jCardView.getLayoutParams();
        lp.height = screenHeight/9;
        jCardView.setLayoutParams(lp);


        ViewGroup.LayoutParams lp1 = sreach_img.getLayoutParams();
        lp1.height = screenHeight/30;
        sreach_img.setLayoutParams(lp1);

        recyclerView.setPadding(0,screenHeight/12,0,0);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                key = s.toString();
                presenter.search(kind,key);
                if(s.length()==0){
                    adapter.clearData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                sreach_img.setVisibility(View.GONE);
                setHint();
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }else{
                if(datas.size()==0){
                    sreach_img.setVisibility(View.VISIBLE);
                }
                hideHint();
            }
        });
    }

    private void setHint(){
        if(kind.equals("老乡群")){
            editText.setHint("请输入地区");
        }else{
            editText.setHint("请输入学院");
        }
    }

    private void hideHint(){
        editText.setHint("");
    }


    @Override
    public void setData(ChatOnline bean) {
        if(bean.getArray().size()>0){
            adapter.refreshData(bean);
            adapter.notifyDataSetChanged();
        }else{
            ToastUtils.show("开发小哥：没有搜索到对应数据噢！");
        }
    }


}
