package com.mredrock.cyxbs.freshman.mvp.presenter.strategy

import android.content.Context
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract.*
import com.mredrock.cyxbs.freshman.mvp.model.strategy.RevealModel
import com.mredrock.cyxbs.freshman.utils.ToastUtils
import com.mredrock.cyxbs.freshman.utils.kt.BasePresenter

class RevealPresenter : BasePresenter<IRevealView, IRevealModel>(), IRevealPresenter {
    override fun onViewCreate() {
        model.getAcademyName(mvpView!!::onGetAcademyName){
            it.printStackTrace()
            ToastUtils.show("网络错误")
        }
    }

    override fun onShowDetail(pos: Int, context: Context) {
        //todo 开新Activity
    }

    override fun createModel(): IRevealModel = RevealModel()

}