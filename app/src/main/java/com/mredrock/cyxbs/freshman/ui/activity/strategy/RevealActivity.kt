package com.mredrock.cyxbs.freshman.ui.activity.strategy

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract.IRevealPresenter
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract.IRevealView
import com.mredrock.cyxbs.freshman.ui.adapter.RevealAdapter
import com.mredrock.cyxbs.freshman.utils.kt.BaseActivity
import com.mredrock.cyxbs.freshman.utils.net.Const
import kotlinx.android.synthetic.main.freshman_activity_strategy_same.*

class RevealActivity : BaseActivity<IRevealView, IRevealPresenter>() {

    val adapter = RevealAdapter()

    override fun createPersenter(): IRevealPresenter = adapter.presenter
    override fun getViewAttachPersenter(): IRevealView = adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_strategy_same)

        toolbar.setNavigationOnClickListener { finish() }
        tb_title.text = Const.INDEX_REVEAL

        rv_strategy.layoutManager = LinearLayoutManager(this)
        rv_strategy.adapter = adapter

        adapter.srl=srl_refresh

    }

}
