package com.yassir.show.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun <T> Context.systemService(name:String) = getSystemService(name) as? T

val Context.windowManager get() = systemService<WindowManager>(Context.WINDOW_SERVICE)

fun Context.screenSize(): Pair<Int, Int> {
    var width = 0 ; var height = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager!!.currentWindowMetrics
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        width = windowMetrics.bounds.width() - insets.left - insets.right
        height = windowMetrics.bounds.height() - insets.top - insets.bottom
    }
    else {
        val displayMetrics = DisplayMetrics()
        windowManager!!.defaultDisplay.getMetrics(displayMetrics)
        width = displayMetrics.widthPixels
        height = displayMetrics.heightPixels
    }
    return Pair(width, height)
}

fun Context.dp2px(dp:Number) = dp.toFloat() * resources.displayMetrics.density

fun Context.color(@ColorRes clr: Int): Int {
    return ContextCompat.getColor(this, clr)
}