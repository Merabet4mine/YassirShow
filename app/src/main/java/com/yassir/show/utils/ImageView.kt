package com.yassir.show.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception


fun ImageView.loadImage(picture:Any?, default:Any?=null, isSuccess:(Boolean) -> Unit = {}) {
    val picasso = Picasso.get()
    var request = when(picture) {
        is String? -> picasso.load(picture)
        is Int     -> picasso.load(picture)
        is Uri -> picasso.load(picture)
        is File -> picasso.load(picture)
        else       -> return
    }
    request = when(default) {
        is Drawable -> request.error(default)
        is Int      -> request.error(default)
        else        -> request
    }
    request.into(this, object: Callback {
        override fun onSuccess() { isSuccess(true) }
        override fun onError(e: Exception?) { isSuccess(false) }
    })
}