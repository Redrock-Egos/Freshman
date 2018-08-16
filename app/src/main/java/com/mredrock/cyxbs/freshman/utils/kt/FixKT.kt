package com.mredrock.cyxbs.freshman.utils.kt

import com.mredrock.cyxbs.freshman.ui.activity.App
import com.mredrock.cyxbs.freshman.utils.DensityUtils
import com.mredrock.cyxbs.freshman.utils.ToastUtils
import com.mredrock.cyxbs.freshman.utils.net.APIService
import com.mredrock.cyxbs.freshman.utils.net.HttpLoader.service
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 你们不用KT，这里我就不写文档了
 */

fun showToast(str: String) = ToastUtils.show(str)

fun <T> getBeanFromNet(success: (T) -> Unit, fail: (Throwable) -> Unit = { throw it }, observable: APIService.() -> Observable<T>) {
    observable(service)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(success, fail)
}

private val wMap = HashMap<Pair<Int, Int>, Float>()
private val hMap = HashMap<Pair<Int, Int>, Float>()

fun getWidth(need: Int, all: Int) =
        wMap[need to all]
                ?: (DensityUtils.getScreenWidth(App.getContext())
                        * need / all.toDouble())
                        .toFloat()
                        .apply { wMap[need to all] = this }

fun getHeight(need: Int, all: Int) =
        hMap[need to all]
                ?: (DensityUtils.getScreenHeight(App.getContext())
                        * need / all.toDouble())
                        .toFloat()
                        .apply { hMap[need to all] = this }