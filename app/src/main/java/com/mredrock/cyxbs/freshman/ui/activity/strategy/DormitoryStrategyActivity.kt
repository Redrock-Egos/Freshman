package com.mredrock.cyxbs.freshman.ui.activity.strategy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.ui.adapter.strategy.DormitoryPageAdapter
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import kotlinx.android.synthetic.main.freshman_activity_dormitory_strategy.*

class DormitoryStrategyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_dormitory_strategy)

        toolbar.setNavigationOnClickListener { finish() }
        DensityUtils.setTransparent(toolbar, this)
        val label = intent.getStringExtra("label")
        tb_title.text = label

        vp_dormitory.adapter = DormitoryPageAdapter()

        vp_dormitory.offscreenPageLimit = 4
        tab_dormitory.setupWithViewPager(vp_dormitory)
    }


}