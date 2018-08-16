package com.mredrock.cyxbs.freshman.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.mvp.model.AdmissionRequestModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.AdmissionRequestPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;
import com.mredrock.cyxbs.freshman.utils.DensityUtils;
import com.mredrock.cyxbs.freshman.utils.SPHelper;
import com.mredrock.cyxbs.freshman.utils.ScrollSpeedLinearLayoutManger;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

public class AdmissionRequestActivity extends AppCompatActivity implements AdmissionRequestContract.IAdmissionRequestView, View.OnClickListener {

    private TextView edit;
    private EditText content;
    private RecyclerView mRv;
    private AdmissionRequestAdapter mAdapter;
    private FloatingActionButton mFabtn;
    private PopupWindow mWindow;
    private AdmissionRequestPresenter mPresenter;

    private boolean isEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_admission_request);
        Toolbar mbar = findViewById(R.id.tb_admission);
        DensityUtils.setTransparent(mbar,this);

        ImageView help = findViewById(R.id.iv_admission_help);
        ImageView back = findViewById(R.id.iv_admission_back);

        mRv = findViewById(R.id.rv_admission);
        mFabtn = findViewById(R.id.fabtn_admission_add);
        edit = findViewById(R.id.tv_admission_edit);

        help.setOnClickListener(this);
        back.setOnClickListener(this);
        mFabtn.setOnClickListener(this);
        edit.setOnClickListener(this);

        initMVP();
    }

    private void initMVP() {
        isEdit = false;
        mPresenter = new AdmissionRequestPresenter(new AdmissionRequestModel());
        mPresenter.attachView(this);
        mPresenter.start();
    }

    @Override
    public void showError() {
        ToastUtils.show(getResources().getString(R.string.freshman_error_soft));
    }

    @Override
    public void initWindow(View.OnClickListener listener) {
        View root = findViewById(R.id.cl_admission);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(App.getContext()).inflate(R.layout.freshman_popup_admission, null);
        Button add = view.findViewById(R.id.btn_admission_sure);
        add.setOnClickListener(listener);
        content = view.findViewById(R.id.et_admission_add);
        mWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mWindow.setOutsideTouchable(true);
        mWindow.setFocusable(true);
        mWindow.setBackgroundDrawable(new ColorDrawable());
        mWindow.setOnDismissListener(this::returnButton);
        mWindow.showAtLocation(root,Gravity.BOTTOM,0,0);
    }

    @Override
    public void setRv(Description description) {
        ScrollSpeedLinearLayoutManger manger = new ScrollSpeedLinearLayoutManger(App.getContext());
        manger.setSpeedSlow();
        mAdapter = new AdmissionRequestAdapter(description.getDescribe(), count -> {
            String total = App.getContext().getResources().getString(R.string.freshmen_admission_delete);
            if (count != 0)
                total = App.getContext().getResources().getString(R.string.freshmen_admission_delete)+"("+count+")";
            edit.setText(total);
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mRv.getItemAnimator().setChangeDuration(100);
        mRv.getItemAnimator().setMoveDuration(200);
    }

    @Override
    public void prepareAddData() {
        mFabtn.setVisibility(View.GONE);
        initWindow(this);
    }

    @Override
    public void addData(Description.DescribeBean temp) {
        mAdapter.add(temp);
        returnButton();
        scrollToPos(mAdapter.getItemCount() - 1);
    }

    @Override
    public void returnButton() {
        content.clearFocus();
        content.setText("");
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (imm != null && v != null) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        mFabtn.setVisibility(View.VISIBLE);
        if (mWindow.isShowing())
            mWindow.dismiss();
    }

    @Override
    public void scrollToPos(int pos) {
        mRv.smoothScrollToPosition(pos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_admission_help:
                mPresenter.showDialog(this);
                break;
            case R.id.iv_admission_back:
                finish();
                break;
            case R.id.tv_admission_edit:
                isEdit = !isEdit;
                if (isEdit) {
                    edit.setText(getResources().getString(R.string.freshmen_admission_delete));
                } else {
                    edit.setText(getResources().getString(R.string.freshmen_admission_edit));
                    mAdapter.deleteDatas();
                }
                mAdapter.changeData(isEdit);
                break;
            case R.id.fabtn_admission_add:
                prepareAddData();
                break;
            case R.id.btn_admission_sure:
                mPresenter.addItem(content.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdapter != null)
            SPHelper.putBean("admission","admission",mAdapter.getDatas());
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return App.getContext();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && isEdit) {
            isEdit = false;
            mAdapter.changeData(false);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
