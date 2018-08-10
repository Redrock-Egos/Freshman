package com.mredrock.cyxbs.freshman.mvp.contract.strategy

import android.content.Context
import com.mredrock.cyxbs.freshman.bean.SexRatio
import com.mredrock.cyxbs.freshman.mvp.presenter.IBasePresenter

class RevealContract {
    interface IRevealView {
        fun onGetAcademyName(names: List<String>)
    }

    interface IRevealModel {
        fun getAcademyName(success: (List<String>) -> Unit, fail: (Throwable) -> Unit = { throw it })
        fun getSexRatio(name: String,success: (SexRatio) -> Unit, fail: (Throwable) -> Unit = { throw it })
    }

    interface IRevealPresenter : IBasePresenter<IRevealView> {
        fun onViewCreate()
        fun onShowDetail(pos: Int, context: Context)
    }
}