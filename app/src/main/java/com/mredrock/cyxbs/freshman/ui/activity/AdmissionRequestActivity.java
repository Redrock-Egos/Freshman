package com.mredrock.cyxbs.freshman.ui.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.contract.AdmissionRequestContract;
import com.mredrock.cyxbs.freshman.model.AdmissionRequestModel;
import com.mredrock.cyxbs.freshman.presenter.AdmissionRequestPresenter;
import com.mredrock.cyxbs.freshman.ui.adapter.AdmissionRequestAdapter;
import com.mredrock.cyxbs.freshman.utils.ToastUtils;

public class AdmissionRequestActivity extends AppCompatActivity implements AdmissionRequestContract.IAdmissionRequestView,View.OnClickListener {

    private TextView edit;
    private EditText content;
    private RecyclerView mRv;
    private FloatingActionButton mFabtn;
    private RelativeLayout mRl;
    private AdmissionRequestPresenter mPresenter;

    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freshman_activity_admission_request);
        mRl = findViewById(R.id.rl_admission_add);
        initMVP();

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
    }

    private void initMVP(){
        isEdit = false;
        mPresenter = new AdmissionRequestPresenter(new AdmissionRequestModel());
        mPresenter.attachView(this);
        mPresenter.start();
        mRl.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showError() {
        ToastUtils.show(getResources().getString(R.string.error));
    }

    @Override
    public void setRv(AdmissionRequestAdapter mAdapter,LinearLayoutManager manager) {
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);
        mRv.getItemAnimator().setChangeDuration(300);
        mRv.getItemAnimator().setMoveDuration(300);
    }

    @Override
    public void setNum(String info) {
        edit.setText(info);
    }

    @Override
    public void addData() {
        mFabtn.setVisibility(View.GONE);
        mRl.setVisibility(View.VISIBLE);
    }

    @Override
    public void returnButton() {
        content.clearFocus();
        mFabtn.setVisibility(View.VISIBLE);
        mRl.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_admission_help:
                mPresenter.showDialog(this);
                break;
            case R.id.iv_admission_back:
                finish();
                break;
            case R.id.tv_admission_edit:
                isEdit = !isEdit;
                if(isEdit) {
                    edit.setText(getResources().getString(R.string.admission_delete));
                } else {
                    edit.setText(getResources().getString(R.string.admission_edit));
                    mPresenter.editRv();
                }
                mPresenter.changeMode(isEdit);
                break;
            case R.id.fabtn_admission_add:
                addData();
                break;
            case R.id.btn_admission_sure:
                String count = content.getText().toString();
                mPresenter.addItem(count);
                break;
        }
    }
}
