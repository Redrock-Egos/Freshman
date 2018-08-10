package com.mredrock.cyxbs.freshman.utils.kt

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mredrock.cyxbs.freshman.ui.activity.App

val gson = Gson()
val Context.defaultSp get() = sp()
val defaultSp get() = App.getContext().sp()

fun sp(name: String = "default") = App.getContext().sp(name)

fun Context.sp(name: String = "default"): SharedPreferences = getSharedPreferences(name, Context.MODE_PRIVATE)

fun Context.sp(name: String = "default",modify: SharedPreferences.Editor.() -> Unit) = sp(name).editor(modify)

fun SharedPreferences.editor(modify: SharedPreferences.Editor.() -> Unit) = edit().apply(modify).apply()

@JvmOverloads
fun <T> getBean(beanName: String, clazz: Class<T>,spName: String = "default"): T? =
    try {
        gson.fromJson(sp(spName).getString(beanName, null), clazz)
    } catch (e: Exception) {
        null
    }

@JvmOverloads
fun <T> putBean(beanName: String, bean: T, spName: String = "default") {
    sp(spName).getString(beanName, gson.toJson(bean))
}
