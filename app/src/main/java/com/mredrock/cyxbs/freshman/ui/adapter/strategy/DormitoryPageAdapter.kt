package com.mredrock.cyxbs.freshman.ui.adapter.strategy

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.SameStrategyContract.ISameStrategyModel
import com.mredrock.cyxbs.freshman.mvp.model.strategy.SameStrategyModel
import com.mredrock.cyxbs.freshman.ui.adapter.BasePagerAdapter
import com.mredrock.cyxbs.freshman.utils.net.Const.*
import kotlinx.android.synthetic.main.freshman_item_dormitory_page.view.*

class DormitoryPageAdapter : BasePagerAdapter<SwipeRefreshLayout, String>(
        listOf(DORMITORY_NAME_1, DORMITORY_NAME_2, DORMITORY_NAME_3, DORMITORY_NAME_4)) {
    override fun SwipeRefreshLayout.initView(mData: String) {
        rv_strategy.layoutManager = LinearLayoutManager(context)
        val fresh = {it: StrategyData ->
            //todo 删除测试数据
            it.details.forEach { detail ->
                detail.picture = List(detail.picture.size) {
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534235341206&di=61be2a2c95a50c0bd3f2d90e3dbe1fd3&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D5b600bc9a2773912c4268569c8188675%2Fa168c4cd7b899e51a2d0776049a7d933c9950dfe.jpg"
                }
            }
            //todo end
            rv_strategy.adapter = StrategyAdapter(it.details)
            isRefreshing = false
        }
        setOnRefreshListener {
            model.getStrategyData(mData, fresh) { it.printStackTrace() }
        }
        model.getStrategyData(mData, fresh) { it.printStackTrace() }
    }

    private val model: ISameStrategyModel = SameStrategyModel()
    override fun getLayoutId() = R.layout.freshman_item_dormitory_page
    override fun getPageTitle(position: Int) = list[position]
}