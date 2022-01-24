package com.yassir.show.sheet

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.yassir.show.R
import com.yassir.show.utils.Popup
import com.yassir.show.utils.onClick

class SheetDemo(context: Context) : Popup(
    context, R.layout.sheet_demo,
    parameter = {
        setGravity(Gravity.CENTER)
        contentBackgroundResource = Color.TRANSPARENT
        // setMargin(0,0,0, context.rootView.marginBottom)
    }
)
{
    // --------------------------------------------------
    private val _ok : View get() = find(R.id._ok)

    // --------------------------------------------------
    fun onCreate(callback:() -> Unit = {}) : SheetDemo {
        super.show()
        onDismiss = {}
        _ok.onClick(Techniques.Pulse) {
            callback()
            dismiss()
        }
        return this
    }

}