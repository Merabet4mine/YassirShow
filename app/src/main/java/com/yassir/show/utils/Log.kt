package com.yassir.show.utils

import android.util.Log


private val Any.tag: String get() = javaClass.simpleName


/**  X.e { String } **/
fun Any.v(msg: () -> String) { if (Log.isLoggable(tag, Log.VERBOSE)) v(msg()) }
fun Any.d(msg: () -> String) { if (Log.isLoggable(tag, Log.DEBUG)) d(msg()) }
fun Any.i(msg: () -> String) { if (Log.isLoggable(tag, Log.INFO)) i(msg()) }
fun Any.e(msg: () -> String) { if (Log.isLoggable(tag, Log.ERROR)) e(msg()) }
fun Any.wtf(msg: () -> String) = w(msg())

/**  X.e(String) **/
fun Any.v(msg: String) = v(tag, msg)
fun Any.d(msg: String) = d(tag, msg)
fun Any.i(msg: String) = i(tag, msg)
fun Any.w(msg: String) = w(tag, msg)
fun Any.e(msg: String) = e(tag, msg)
fun Any.wtf(msg: String) = wtf(tag, msg)

/**  X.e(String, String) **/
fun v(tag: String, msg: String) = Log.v(tag, msg)
fun d(tag: String, msg: String) = Log.d(tag, msg)
fun i(tag: String, msg: String) = Log.i(tag, msg)
fun w(tag: String, msg: String) = Log.w(tag, msg)
fun e(tag: String, msg: String) = Log.e(tag, msg)
fun wtf(tag: String, msg: String) = Log.wtf(tag, msg)


