package com.mredrock.cyxbs.freshman.utils.kt

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mredrock.cyxbs.freshman.mvp.presenter.IBasePresenter

@SuppressLint("Registered")
abstract class BaseActivity<V, P : IBasePresenter<V>> : AppCompatActivity() {

    protected val persenter: P by lazy { createPersenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        persenter.attachView(getViewAttachPersenter())
    }

    abstract fun getViewAttachPersenter(): V

    override fun onDestroy() {
        super.onDestroy()
        persenter.detachView()
    }

    abstract fun createPersenter() : P
}