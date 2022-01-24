package com.yassir.show.utils

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


fun View.onClick(anim:Techniques = Techniques.RubberBand, callback:() -> Unit){
    setOnClickListener {
        YoYo.with(anim)
            .duration(150)
            .onEnd { callback() }
            .playOn(this@onClick)
    }
}