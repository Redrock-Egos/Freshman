package com.mredrock.cyxbs.freshman.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.github.chrisbanes.photoview.PhotoView
import com.mredrock.cyxbs.freshman.ui.adapter.BasePagerAdapter
import com.mredrock.cyxbs.freshman.utils.kt.g

fun start(context: Context, photoList: List<String>, pos: Int = 0) {
    context.startActivity(
            Intent(context, PhotoViewerActivity::class.java).apply {
                putExtra("photos", photoList.toTypedArray())
                putExtra("position", pos)
            })
}

class PhotoViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val list = intent.extras.getStringArray("photos")
        ViewPager(this).apply {
            setContentView(this@apply)
            adapter = object : BasePagerAdapter<PhotoView, String>(list.toList()) {
                override fun createView(context: Context) = PhotoView(context)
                override fun PhotoView.initView(mData: String) {
                    g.load(mData).thumbnail(0.1f).into(this)
                    setOnClickListener { finish() }
                }
            }
            currentItem = intent.extras.getInt("position")
            setBackgroundColor(Color.parseColor("#000000"))
        }
    }
}
