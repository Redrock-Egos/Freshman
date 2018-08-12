package com.mredrock.cyxbs.freshman.ui.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mredrock.cyxbs.freshman.R;

import com.mredrock.cyxbs.freshman.bean.Description;
import com.mredrock.cyxbs.freshman.mvp.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.mvp.model.AdmissionRequestModel;
import com.mredrock.cyxbs.freshman.mvp.presenter.AdmissionRequestPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;
import com.mredrock.cyxbs.freshman.utils.SPHelper;
import com.mredrock.cyxbs.freshman.utils.ScrollSpeedLinearLayoutManger;
import com.mredrock.cyxbs.freshman.utils.StatusBarUtils;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

public class AdmissionRequestActivity extends AppCompatActivity implements AdmissionRequestContract.IAdmissionRequestView, View.OnClickListener {

    private TextView edit;
    private EditText content;
    private RecyclerView mRv;
    private AdmissionRequestAdapter mAdapter;
    private FloatingActionButton mFabtn;
    private RelativeLayout mRl;
    private AdmissionRequestPresenter mPresenter;

    private boolean isEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_admission_request);
        StatusBarUtils.setImage(this);

        mRl = findViewById(R.id.rl_admission_add);
        ImageView help = findViewById(R.id.iv_admission_help);
        ImageView back = findViewById(R.id.iv_admission_back);
        Button add = findViewById(R.id.btn_admission_sure);
        content = findViewById(R.id.et_admission_add);
        edit = findViewById(R.id.tv_admission_edit);
        mRv = findViewById(R.id.rv_admission);
        mFabtn = findViewById(R.id.fabtn_admission_add);


        help.setOnClickListener(this);
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        mFabtn.setOnClickListener(this);
        add.setOnClickListener(this);

        initMVP();
    }

    private void initMVP() {
        isEdit = false;
        mPresenter = new AdmissionRequestPresenter(new AdmissionRequestModel());
        mPresenter.attachView(this);
        mPresenter.start();
        mRl.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        ToastUtils.show(getResources().getString(R.string.freshmen_error));
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
        mRv.setLayoutManager(manger);
        mRv.setAdapter(mAdapter);
        mRv.getItemAnimator().setChangeDuration(200);
        mRv.getItemAnimator().setMoveDuration(800);
    }

    @Override
    public void prepareAddData() {
        mFabtn.setVisibility(View.GONE);
        mRl.setVisibility(View.VISIBLE);
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
        mRl.setVisibility(View.GONE);
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
        SPHelper.putBean("admission","admission",mAdapter.getDatas());
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return App.getContext();
    }
}
