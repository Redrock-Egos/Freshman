package com.mredrock.cyxbs.freshman.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

fun start(context: Context, photoList: List<String>) {
    context.startActivity(
            Intent(context, PhotoViewerActivity::class.java).apply {
                putExtra("photos", photoList.toTypedArray())
            })
}

class PhotoViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val list = intent.extras.getStringArray("photos")
        val g = Glide.with(this)
        ViewPager(this).apply {
            setContentView(this@apply)
            adapter = object : PagerAdapter() {
                override fun getCount(): Int {
                    return list.size
                }

                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view === `object`
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View)
                }

                override fun getItemPosition(`object`: Any): Int {
                    return PagerAdapter.POSITION_NONE
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    return PhotoView(container.context).apply pv@ {
                        g.load(list[position]).into(this@pv)
                        container.addView(this@pv)
                    }
                }

            }
        }
    }
}
