package com.yassir.show.utils

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity

abstract class Activity(private val layout:Int): AppCompatActivity() {

    // ACTIVITY ----------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.layout_margin(insets.left, 0, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }
        onCreate()
    }

    // OTHER -------------------------------------------
    open fun onCreate() {}

    // --------------------------------------------------
    private val rootView get() = findViewById<ViewGroup>(android.R.id.content)[0]
    private fun View.layout_margin(left:Int?=null, top:Int?=null, right:Int?=null, bottom:Int?=null) {
        val params = (layoutParams as? ViewGroup.MarginLayoutParams)
        params?.setMargins(
            left ?: params.leftMargin,
            top ?: params.topMargin,
            right ?: params.rightMargin,
            bottom ?: params.bottomMargin
        )
        layoutParams = params
    }

}