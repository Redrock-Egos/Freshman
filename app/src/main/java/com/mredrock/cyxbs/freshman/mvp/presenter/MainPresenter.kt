package com.mredrock.cyxbs.freshman.mvp.presenter

import android.animation.*
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.mvp.contract.MainContract.*
import com.mredrock.cyxbs.freshman.mvp.model.MainModel
import com.mredrock.cyxbs.freshman.ui.activity.AdmissionRequestActivity
import com.mredrock.cyxbs.freshman.ui.activity.CquptMienActivity
import com.mredrock.cyxbs.freshman.ui.activity.MilitaryTrainingActivity
import com.mredrock.cyxbs.freshman.utils.kt.BasePresenter

class MainPresenter : BasePresenter<IMainView, IMainModel>(), IMainPresenter {
    override fun onContentViewSet() = mvpView!!.initBuilding(model.getNowStop())

    private var isAnimationPlaying = false
    private inline fun Boolean.then(then: () -> Unit) {
        if (this) then()
    }

    override fun onBuildingClick(view: ImageView) = (!isAnimationPlaying).then {
        when (view.id) {
            R.id.freshman_building_request1 -> 1
            R.id.freshman_building_strategy2 -> 2
            R.id.freshman_building_chat3 -> 3
            R.id.freshman_building_process4 -> 4
            R.id.freshman_building_words5 -> 5
            R.id.freshman_building_mien -> 6
            R.id.freshman_building_military -> 7
            else -> throw Exception("it's not building")
        }.let {
            val nextStop = model.getNowStop() + 1
            if (it < nextStop || it > 5) {
                startActivity(it)
            } else if (it == nextStop) {
                isAnimationPlaying = true
                driveAnimator(it)
            }
            Unit
        }
    }

    private val carScale by lazy {
        arrayOf(0.4762f, 0.5556f, 0.7143f, 0.8741f, 1f)
                .map { it * mvpView!!.getScale() }
    }

    private fun driveAnimator(pos: Int) {
        mvpView?.apply {

            val now = getCars()[pos - 2]
            val pass = getPassCar(pos)
            val target = getCars()[pos - 1]

            AnimatorSet().apply {
                duration = 1000
                interpolator = AccelerateDecelerateInterpolator()
                playTogether(
                        ValueAnimator.ofFloat(1f, carScale[pos - 1] / carScale[pos - 2])
                                .apply {
                                    addUpdateListener {
                                        now.scaleX = it.animatedValue as Float
                                        now.scaleY = now.scaleX
                                    }
                                },
                        ObjectAnimator.ofFloat(now, "translationX",
                                0f, pass.x - now.x, target.x - now.x),
                        ObjectAnimator.ofFloat(now, "translationY",
                                0f, pass.y - now.y, target.y - now.y).apply {
                            (pos > 2).then {
                                val half = pass.y - now.y
                                addUpdateListener {
                                    if (it.animatedValue as Float > half) {
                                        val bitmap = BitmapFactory.decodeResource(mvpView!!.context.resources,
                                                if (pos == 4) R.drawable.freshman_img_car_left
                                                else R.drawable.freshman_img_car_right)
                                        now.setImageBitmap(android.graphics.Bitmap.createScaledBitmap(bitmap,
                                                (carScale[pos - 2] * bitmap.width).toInt(),
                                                (carScale[pos - 2] * bitmap.height).toInt(),
                                                true))
                                        bitmap.recycle()
                                        removeAllUpdateListeners()
                                    }
                                }
                            }
                        })
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        model.setNowStop(pos)
                        isAnimationPlaying = false
                        unlockBuilding(pos)
                        startActivity(pos)
                    }
                })
            }.start()
        }
    }

    override fun onResume() {
        with(mvpView!!.getCars()) {
            forEach {
                it.visibility = View.INVISIBLE
            }
            val pos = model.getNowStop()
            val bitmap = BitmapFactory.decodeResource(mvpView!!.context.resources, when (pos) {
                1, 2, 4 -> R.drawable.freshman_img_car_left
                else -> R.drawable.freshman_img_car_right
            })
            with(get(pos - 1)) {
                setImageBitmap(Bitmap.createScaledBitmap(bitmap,
                        (carScale[pos - 1] * bitmap.width).toInt(),
                        (carScale[pos - 1] * bitmap.height).toInt(),
                        true))
                visibility = View.VISIBLE
            }
            bitmap.recycle()

        }
    }


    /**
     * 点击建筑物启动的Activity，改成对应的class
     */
    private fun startActivity(pos: Int) = mvpView?.context?.let {
        it.startActivity(Intent(it, when (pos) {
            1 -> AdmissionRequestActivity::class
            2 -> AdmissionRequestActivity::class
            3 -> AdmissionRequestActivity::class
            4 -> AdmissionRequestActivity::class
            5 -> AdmissionRequestActivity::class
            6 -> CquptMienActivity::class
            7 -> MilitaryTrainingActivity::class
            else -> throw Exception("no activity found")
        }.java))
    }


    override fun createModel(): IMainModel = MainModel()

}