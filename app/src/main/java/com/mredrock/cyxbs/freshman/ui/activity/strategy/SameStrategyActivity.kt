package com.mredrock.cyxbs.freshman.ui.activity.strategy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.SameStrategyContract.ISameStrategyPresenter
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.SameStrategyContract.ISameStrategyView
import com.mredrock.cyxbs.freshman.mvp.presenter.strategy.SameStrategyPresenter
import com.mredrock.cyxbs.freshman.ui.adapter.strategy.BankAdapter
import com.mredrock.cyxbs.freshman.ui.adapter.strategy.StrategyAdapter
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import com.mredrock.cyxbs.freshman.utils.kt.BaseActivity
import com.mredrock.cyxbs.freshman.utils.net.Const.*
import kotlinx.android.synthetic.main.freshman_activity_strategy_same.*

fun createStrategyActivity(context: Context, label: String) {
    context.startActivity(Intent(context,
            when (label) {
                INDEX_DORMITORY -> DormitoryStrategyActivity::class.java
                INDEX_REVEAL -> RevealActivity::class.java
                else -> SameStrategyActivity::class.java
            }).apply {
        putExtra("label", label)
    })

}
class SameStrategyActivity : BaseActivity<ISameStrategyView, ISameStrategyPresenter>(), ISameStrategyView {
    override fun refreshView(mData: StrategyData) {
        //todo 删除测试数据
        mData.details.forEach { it ->
            it.picture = List(it.picture.size) {
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534235341206&di=61be2a2c95a50c0bd3f2d90e3dbe1fd3&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D5b600bc9a2773912c4268569c8188675%2Fa168c4cd7b899e51a2d0776049a7d933c9950dfe.jpg"
            }
        }
        //todo end
        rv_strategy.adapter =
                when (intent.getStringExtra("label")) {
                    INDEX_BANK, INDEX_EXPRESS -> BankAdapter(mData.details)
                    else -> StrategyAdapter(mData.details)
                }
        srl_refresh.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_strategy_same)

        toolbar.setNavigationOnClickListener { finish() }
        DensityUtils.setTransparent(toolbar,this)
        val label = intent.getStringExtra("label")
        tb_title.text = label

        rv_strategy.layoutManager = LinearLayoutManager(this)

        if (label == INDEX_CATE) {
            best_cate_ever.visibility = View.VISIBLE
        }

        persenter.refreshData(label)
        srl_refresh.setOnRefreshListener{ persenter.refreshData(label)}
    }


    override fun getViewAttachPersenter() = this
    override fun createPersenter() = SameStrategyPresenter()
}
