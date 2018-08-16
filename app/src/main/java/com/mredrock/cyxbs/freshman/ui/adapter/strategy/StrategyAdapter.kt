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
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.makeramen.roundedimageview.RoundedImageView
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.StrategyData
import com.mredrock.cyxbs.freshman.ui.activity.start
import com.mredrock.cyxbs.freshman.ui.adapter.BasePagerAdapter
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import com.mredrock.cyxbs.freshman.utils.LogUtils
import kotlinx.android.synthetic.main.freshman_item_strategy.view.*

open class StrategyAdapter(private val list: List<StrategyData.DetailData>) : RecyclerView.Adapter<StrategyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_strategy, parent, false)
        val context = parent.context
        if (g == null) { g = Glide.with(context) }
        if (dp6 == 0f) { dp6 = DensityUtils.dp2px(context, 6f) }
        if (lParams == null) {
            val dp4 = DensityUtils.dp2px(context, 4f).toInt()
            lParams = LinearLayout.LayoutParams(dp4 * 2, dp4 * 2)
            lParams!!.setMargins(dp4, dp4, dp4, dp4)
        }
        if (pointWidth == 0) {
            v.apply {
                ll_point.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        ll_point.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        if (ll_point.childCount < 2) {
                            return
                        }
                        pointWidth = ll_point.getChildAt(0).left - ll_point.getChildAt(1).left
                    }
                })
            }
        }
        if (imagePageHeight == 0) {
            imagePageHeight = (0.2458 * DensityUtils.getScreenHeight(context)).toInt()
        }
        v.apply {
            vp_strategy.layoutParams.height = imagePageHeight
            vp_strategy.addOnPageChangeListener(IndexPageListener(iv_index_point))
        }
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: StrategyAdapter.ViewHolder, position: Int) = holder.v.initView(list[position], position)

    private var pointWidth = 0
    private var imagePageHeight = 0
    private var g: RequestManager? = null
    private var dp6 = 0f
    private var lParams:LinearLayout.LayoutParams?=null

    private fun View.initView(mData: StrategyData.DetailData, pos: Int) {
        tv_describe_name.text = mData.name
        tv_describe_detail.text = mData.content

        with(ll_point) {
            for (i in childCount until mData.picture.size) {
                addView(ImageView(context).apply {
                    layoutParams = lParams
                    setImageResource(R.drawable.freshman_ic_point_gray)
                })
            }
        }

        vp_strategy.adapter = ImagePageAdapter(mData.picture, pos)
    }

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v)

    inner class IndexPageListener(private val indexView: ImageView) : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            indexView.translationX = -(position + positionOffset) * pointWidth
        }
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageSelected(position: Int) {}
    }

    private inner class ImagePageAdapter(private val picUrls: List<String>, val pos: Int) : BasePagerAdapter<RoundedImageView, String>(picUrls) {
        override fun createView(context: Context) = RoundedImageView(context)
        override fun com.makeramen.roundedimageview.RoundedImageView.initView(mData: kotlin.String) {
            adjustViewBounds = true
            cornerRadius = dp6
            scaleType = ImageView.ScaleType.CENTER_CROP
            setOnClickListener { _ ->
                start(context, picUrls, pos)
            }
            g?.load(mData)
                    ?.thumbnail(0.1f)
                    ?.diskCacheStrategy(DiskCacheStrategy.ALL)
                    ?.into(this)
        }
    }
}