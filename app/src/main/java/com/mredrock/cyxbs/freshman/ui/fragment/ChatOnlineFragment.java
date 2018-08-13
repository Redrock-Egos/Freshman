package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.ChatOnline;
import com.mredrock.cyxbs.freshman.mvp.contract.ChatOnlineContract;
import com.mredrock.cyxbs.freshman.mvp.model.ChatOnlineModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.ChatOnlinePresneter;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ChatOnlineFragment extends Fragment implements ChatOnlineContract.IChatOnlineView{
    private Context context;
    private String kind;
    private View parent;

    private EditText editText;
    private ListView listView;
    private int screenWidth;
    private ChatOnlinePresneter presneter;
    private List<String>  datas;
    private ArrayAdapter<String> adapter;

    public ChatOnlineFragment(Context context, String kind) {
        this.context = context;
        this.kind = kind;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.freshman_chatonline_fragment,container,false);
        initP();
        init();
        setET();
        return parent;
    }

    private void initP(){
        presneter = new ChatOnlinePresneter(new ChatOnlineModel());
        presneter.attachView(this);
    }

    private void init(){
        editText = parent.findViewById(R.id.freshman_chatonline_et);
        listView = parent.findViewById(R.id.freshman_chatonline_lv);
        datas = new ArrayList<>();
    }
    private void setET(){
        adapter = new ArrayAdapter<String>(getContext(),R.layout.freshman_item_chatonline_lv,datas);
        listView.setAdapter(adapter);
        screenWidth = DensityUtils.getScreenHeight(getContext());
        ViewGroup.LayoutParams lp = editText.getLayoutParams();
        lp.height = screenWidth/12;
        editText.setLayoutParams(lp);
        Drawable drawable = editText.getCompoundDrawables()[0];
        if(drawable!=null){
            drawable.setBounds(0,0,30,30);
            editText.setCompoundDrawables(drawable,editText.getCompoundDrawables()[1],editText.getCompoundDrawables()[2], editText.getCompoundDrawables()[3]);
        }
        if(kind.equals("学校群")){
            editText.setHint("请输入学院名称/班级代号");
        }else{
            editText.setHint("请输入地区");
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    presneter.search(kind,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void setData(ChatOnline bean) {
        datas.clear();
        for (int i = 0; i < bean.getArray().size(); i++) {
            datas.add(bean.getArray().get(i).getName());
        }
        adapter.notifyDataSetChanged();
    }
}
