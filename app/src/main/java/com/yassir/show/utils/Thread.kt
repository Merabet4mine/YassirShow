package com.yassir.show.utils

import android.os.Handler
import android.os.Looper

private fun isMainLooperAlive() = Looper.myLooper() == Looper.getMainLooper()

fun runOnUiThread(action: () -> Unit) {
    if (isMainLooperAlive()) { action() }
    else { Handler(Looper.getMainLooper()).post(Runnable(action)) }
}