package com.mredrock.cyxbs.freshman.ui.adapter.strategy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.ui.activity.start
import com.mredrock.cyxbs.freshman.utils.kt.dp
import kotlinx.android.synthetic.main.freshman_item_strategy_bank.view.*

class BankAdapter(val list: List<StrategyData.DetailData>) : RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        if (g == null) {
            g = Glide.with(parent.context)
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_strategy_bank, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.v.initView(list[position])

    class ViewHolder(val v:View) : RecyclerView.ViewHolder(v)

    private var g: RequestManager? = null

    private fun View.initView(mData: StrategyData.DetailData) {
        tv_name.text = mData.name
        tv_name.layoutParams.height = dp(18)

        tv_detail.text = mData.content
        tv_detail.setLineSpacing(dp(6).toFloat(), 1f)
        tv_detail.layoutParams.height = dp(36)

        rl.layoutParams.height = dp(89)
        iv_img.apply {
            iv_img.layoutParams.width = dp(102)
            setOnClickListener { start(context, mData.picture) }
            g?.load(mData.picture.first())?.into(this)
        }
    }
}