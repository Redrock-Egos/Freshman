package com.mredrock.cyxbs.freshman.ui.activity.strategy

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract.IRevealPresenter
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract.IRevealView
import com.mredrock.cyxbs.freshman.mvp.presenter.strategy.RevealPresenter
import com.mredrock.cyxbs.freshman.ui.adapter.RevealAdapter
import com.mredrock.cyxbs.freshman.utils.kt.BaseActivity
import kotlinx.android.synthetic.main.freshman_activity_reveal.*

class RevealActivity : BaseActivity<IRevealView, IRevealPresenter>(),IRevealView {
    override fun createPersenter(): IRevealPresenter = RevealPresenter()
    override fun getViewAttachPersenter(): IRevealView = this

    val adapter = RevealAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_reveal)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        rv_reveal.layoutManager = LinearLayoutManager(this)
        rv_reveal.adapter = adapter

        persenter.onViewCreate()
    }

    override fun onGetAcademyName(names: List<String>) {

    }
}
