package com.yassir.show.utils

import android.view.View
import android.content.Context
import com.orhanobut.dialogplus.ViewHolder
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.DialogPlusBuilder

// REQUIRED : implementation 'com.orhanobut:dialogplus:1.11@aar'
abstract class Popup(val context: Context, layout:Int, parameter: DialogPlusBuilder.() -> Unit = {}) {

    // ------------------------------
    protected var dialog : DialogPlus
    init {
        val builder = DialogPlus.newDialog(context)
        builder.setContentHolder(ViewHolder(layout))
        builder.setOnDismissListener { onDismiss() }
        parameter(builder)
        dialog = builder.create()
    }

    // ------------------------------
    val isShowing get() = dialog.isShowing
    var onDismiss : () -> Unit = {}

    // ------------------------------
    open fun show() { dialog.show() }
    open fun dismiss() { dialog.dismiss() }
    open fun onBackPressed() { dialog.onBackPressed(dialog) }

    // ------------------------------
    protected fun <T: View> find(id:Int): T = dialog.holderView.findViewById(id)

}