package com.mredrock.cyxbs.freshman.mvp.model.strategy

import com.mredrock.cyxbs.freshman.bean.SexRatio
import com.mredrock.cyxbs.freshman.mvp.contract.strategy.RevealContract
import com.mredrock.cyxbs.freshman.utils.kt.getBean
import com.mredrock.cyxbs.freshman.utils.kt.getBeanFromNet

class RevealModel : RevealContract.IRevealModel {
    override fun getAcademyName(success: (List<String>) -> Unit, fail: (Throwable) -> Unit) {
        //todo 感谢lbz提供的缓存库
        getBeanFromNet({success(it.name)},fail){academyName}
    }

    override fun getSexRatio(name: String, success: (SexRatio) -> Unit, fail: (Throwable) -> Unit) {
        getBean("${name}SexRatio", SexRatio::class.java)?.let(success)
        getBeanFromNet(success, fail) { getSexRatio(name) }
    }

}