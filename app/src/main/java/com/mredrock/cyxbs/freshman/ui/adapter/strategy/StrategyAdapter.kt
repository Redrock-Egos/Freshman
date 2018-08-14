package com.mredrock.cyxbs.freshman.ui.adapter.strategy

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.ui.activity.start
import com.mredrock.cyxbs.freshman.ui.adapter.BasePagerAdapter
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import kotlinx.android.synthetic.main.freshman_item_strategy.view.*

class StrategyAdapter(private val list: List<StrategyData.DetailData>) : RecyclerView.Adapter<StrategyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_strategy, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: StrategyAdapter.ViewHolder, position: Int) = holder.v.initView(list[position], position)

    private fun View.initView(mData: StrategyData.DetailData, pos: Int) {
        tv_describe_name.text = mData.name
        tv_describe_detail.text = mData.content

        val g = Glide.with(context)

        val dp4 = DensityUtils.dp2px(context, 4f).toInt()
        val lParams = LinearLayout.LayoutParams(dp4 * 2, dp4 * 2)
        lParams.setMargins(dp4, dp4, dp4, dp4)
        with(ll_point) {
            for (i in ll_point.childCount until mData.picture.size) {
                addView(ImageView(context).apply {
                    layoutParams = lParams
                    setImageResource(R.drawable.freshman_ic_point_gray)
                })
            }

            for (i in mData.picture.size until ll_point.childCount) {
                removeViewAt(i)
            }
        }

        var pointWidth = 0;
        ll_point.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                ll_point.viewTreeObserver.removeOnGlobalLayoutListener(this)
                if (ll_point.childCount < 2) {
                    return
                }
                pointWidth = ll_point.getChildAt(0).left - ll_point.getChildAt(1).left
            }
        })


        val dp6 = DensityUtils.dp2px(context, 6f)
        vp_strategy.layoutParams.height = (0.2458 * DensityUtils.getScreenHeight(context)).toInt()
        vp_strategy.adapter = object : BasePagerAdapter<RoundedImageView, String>(mData.picture) {
            override fun createView(context: Context) = RoundedImageView(context)
            override fun RoundedImageView.initView(mData: String) {
                adjustViewBounds = true
                cornerRadius = dp6
                scaleType = ImageView.ScaleType.CENTER_CROP
                setOnClickListener { _ ->
                    start(context, list, pos)
                }
                g.load(mData).into(this)
            }
        }

        vp_strategy.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                iv_index_point.translationX = -(position + positionOffset) * pointWidth
            }

            override fun onPageSelected(position: Int) {}

        })

    }

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v)

}