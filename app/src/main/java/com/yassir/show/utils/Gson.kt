package com.yassir.show.utils

import com.google.gson.GsonBuilder


val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
fun Any.toJson() : String = gson.toJson(this)
inline fun <reified T> String.fromJson() : T? = gson.fromJson(this,T::class.java)
inline fun <reified T> Any?.convert() : T? = this?.toJson()?.fromJson() ?: null