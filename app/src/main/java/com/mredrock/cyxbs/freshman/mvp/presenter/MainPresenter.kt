package com.mredrock.cyxbs.freshman.mvp.presenter

import android.content.Intent
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.mvp.contract.MainContract.*
import com.mredrock.cyxbs.freshman.mvp.model.MainModel
import com.mredrock.cyxbs.freshman.ui.activity.AdmissionRequestActivity
import com.mredrock.cyxbs.freshman.ui.activity.CquptMienActivity
import com.mredrock.cyxbs.freshman.ui.activity.MilitaryTrainingActivity
import com.mredrock.cyxbs.freshman.utils.kt.BasePresenter

class MainPresenter : BasePresenter<IMainView, IMainModel>(), IMainPresenter {

    override fun onContentViewSet() = mvpView!!.initBuilding(model.getNowStop())
    override fun onResume() = mvpView!!.initCars(model.getNowStop())

    private var isAnimationPlaying = false
    private inline fun Boolean.then(then: () -> Unit) {
        if (this) then()
    }
    override fun onBuildingClick(id: Int) = (!isAnimationPlaying).then {
        when (id) {
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
                mvpView?.driveAnimator(it)
            }
            Unit
        }
    }

    override fun onDriveEnd(pos: Int) {
        model.setNowStop(pos)
        startActivity(pos)
        isAnimationPlaying = false
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