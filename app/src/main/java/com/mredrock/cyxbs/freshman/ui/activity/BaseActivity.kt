package com.mredrock.cyxbs.freshman.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import kotlinx.android.synthetic.main.freshman_include_toolbar.*

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutResID(): Int
    abstract fun getToolbarTitle(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResID())
        DensityUtils.setTransparent(toolbar, this)
        tv_toolbar_title.text = getToolbarTitle()
        toolbar.setNavigationOnClickListener { finish() }
    }
}