package com.mredrock.cyxbs.freshman.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.ChatOnline;
import com.mredrock.cyxbs.freshman.mvp.contract.ChatOnlineContract;
import com.mredrock.cyxbs.freshman.mvp.model.ChatOnlineModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.ChatOnlinePresenter;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ChatOnlineFragment extends Fragment implements ChatOnlineContract.IChatOnlineView{
    private Context context;
    private String kind;
    private View parent;
    private String key;

    private EditText editText;
    private ListView listView;
    private int screenHeight;
    private ChatOnlinePresenter presenter;
    private List<String> datas;
    private ArrayAdapter<String> adapter;
    private LinearLayout ll;

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
        presenter = new ChatOnlinePresenter(new ChatOnlineModel());
        presenter.attachView(this);
    }

    private void init(){
        editText = parent.findViewById(R.id.freshman_chatonline_et);
        listView = parent.findViewById(R.id.freshman_chatonline_lv);
        ll = parent.findViewById(R.id.freshman_chatonline_parent);
        datas = new ArrayList<>();
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setET(){
        adapter = new ArrayAdapter<String>(getContext(),R.layout.freshman_item_chatonline_lv,datas);
        listView.setAdapter(adapter);
        listView.setDividerHeight(0);
        screenHeight = DensityUtils.getScreenHeight(getContext());
        ViewGroup.LayoutParams lp = editText.getLayoutParams();
        lp.height = screenHeight/15;
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
                    key = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnKeyListener((v, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_DEL){
                datas.clear();
                adapter.notifyDataSetChanged();
            }
            if(keyCode == KeyEvent.KEYCODE_ENTER){
                presenter.search(kind,key);
            }
            return false;
        });
    }


    @Override
    public void setData(ChatOnline bean) {
        datas.clear();
        for (int i = 0; i < bean.getArray().size(); i++) {
            datas.add(bean.getArray().get(i).getName());
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            ViewGroup.LayoutParams lp1 = listView.getChildAt(i).getLayoutParams();
            lp1.height = screenHeight/15;
            listView.getChildAt(i).setLayoutParams(lp1);
        }

        adapter.notifyDataSetChanged();
    }


}
