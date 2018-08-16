package com.mredrock.cyxbs.freshman.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import kotlinx.android.synthetic.main.freshman_activity_words.*

class WordsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_words)

        DensityUtils.setTransparent(toolbar, this)
        toolbar.setNavigationOnClickListener { finish() }
    }
}
