package com.mredrock.cyxbs.freshman.mvp.contract

import android.widget.ImageView
import com.mredrock.cyxbs.freshman.mvp.presenter.IBasePresenter

class MainContract {

    interface IMainView : BaseContract.ISomethingView {
        fun initBuilding(pos: Int)
        fun getCars(): Array<ImageView>
        fun getPassCar(pos: Int): ImageView
        fun getScale(): Float
        fun unlockBuilding(pos: Int)
    }

    interface IMainModel {
        fun getNowStop(): Int
        fun setNowStop(nowStop: Int)
    }

    interface IMainPresenter : IBasePresenter<IMainView> {
        fun onBuildingClick(view: ImageView)
        fun onContentViewSet()
        fun onResume()
    }
}