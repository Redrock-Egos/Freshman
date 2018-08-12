package com.mredrock.cyxbs.freshman.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.mvp.contract.MainContract
import com.mredrock.cyxbs.freshman.mvp.presenter.MainPresenter
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import com.mredrock.cyxbs.freshman.utils.kt.BaseActivity
import kotlinx.android.synthetic.main.freshman_activity_main.*

class MainActivity : BaseActivity<MainContract.IMainView, MainContract.IMainPresenter>(), MainContract.IMainView {
    private val mCars by lazy { arrayOf(car1, car2, car3, car4, car5) }
    override fun getCars(): Array<ImageView> = mCars
    private val mPassCars by lazy { arrayOf(car1_2, car2_3, car3_4, car4_5) }
    override fun getPassCar(pos: Int): ImageView = mPassCars[pos - 2]

    private val scale by lazy { DensityUtils.getScreenWidth(this).toDouble() / iv_bg.drawable.intrinsicWidth }
    override fun getScale(): Float = scale.toFloat()

    private val buildings by lazy {
        arrayOf(
                freshman_building_request1 to (R.drawable.freshman_building_request to R.drawable.freshman_building_request),
                freshman_building_strategy2 to (R.drawable.freshman_building_strategy_unlock to R.drawable.freshman_building_strategy_locked),
                freshman_building_chat3 to (R.drawable.freshman_building_chat_unlock to R.drawable.freshman_building_chat_locked),
                freshman_building_process4 to (R.drawable.freshman_building_process_unlock to R.drawable.freshman_building_process_locked),
                freshman_building_words5 to (R.drawable.freshman_building_words_unlock to R.drawable.freshman_building_words_locked))
    }

    override fun initBuilding(pos: Int) =
            buildings.forEachIndexed { i, building ->
                setScaledBitmap(building.first, building.second.first, building.second.second, pos <= i)
            }.also {
                setScaledBitmap(freshman_building_mien, R.drawable.freshman_building_mien)
                setScaledBitmap(freshman_building_military, R.drawable.freshman_building_military)
            }

    override fun unlockBuilding(pos: Int) =
            setScaledBitmap(buildings[pos - 1].first, buildings[pos - 1].second.first)

    private fun setScaledBitmap(view: ImageView, unlockId: Int, lockedId: Int = unlockId, isLocked: Boolean = false) {
        val bitmap = BitmapFactory.decodeResource(resources, if (isLocked) lockedId else unlockId)
        view.setImageBitmap(Bitmap.createScaledBitmap(bitmap, (scale * bitmap.width).toInt(), (scale * bitmap.height).toInt(), true))
        bitmap.recycle()
    }

    override fun getContext() = this
    override fun getViewAttachPersenter(): MainContract.IMainView = this
    override fun createPersenter(): MainContract.IMainPresenter = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_main)
        persenter.onContentViewSet()
    }

    override fun onResume() {
        super.onResume()
        persenter.onResume()
    }

    fun onBuildingClick(view: View) = (view as? ImageView)?.let { persenter.onBuildingClick(it) }
}
