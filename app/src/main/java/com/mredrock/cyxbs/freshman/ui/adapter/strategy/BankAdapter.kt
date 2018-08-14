package com.mredrock.cyxbs.freshman.ui.adapter.strategy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.ui.activity.start
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import kotlinx.android.synthetic.main.freshman_item_strategy_bank.view.*

class BankAdapter(val list: List<StrategyData.DetailData>) : RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_strategy_bank, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.v.initView(list[position])

    class ViewHolder(val v:View) : RecyclerView.ViewHolder(v)

    private fun View.initView(mData: StrategyData.DetailData) {
        tv_name.text = mData.name

//       todo 视觉小姐姐说只有两行文字！ tv_detail.text = mData.content
        tv_detail.text="视觉小姐姐说了只有两行！\n但是web的小哥哥却给了三行"

        val scale89 =(89.0 / 375 * DensityUtils.getScreenWidth(context)).toInt()
        rl . layoutParams . height = scale89
        iv_img.apply {
            iv_img.layoutParams.width = (102.0 / 89 * scale89).toInt()
            setOnClickListener { start(context, mData.picture) }
            Glide.with(context).load(mData.picture[0]).into(this)
        }
    }
}