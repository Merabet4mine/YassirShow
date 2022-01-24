package com.yassir.show.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.GridLayoutManager


fun RecyclerView.linearLayout(orientation:Int= RecyclerView.VERTICAL, pagerSnap:Boolean=false){
    isFocusable = false
    setHasFixedSize(true)
    layoutManager = LinearLayoutManager(context,orientation,false)
    if (pagerSnap) PagerSnapHelper().attachToRecyclerView(this)
}
fun RecyclerView.gridLayout(spanCount:Int){
    isFocusable = false
    setHasFixedSize(true)
    layoutManager = GridLayoutManager(context, spanCount)
}