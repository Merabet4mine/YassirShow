package com.yassir.show.utils

import kotlin.math.min
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
abstract class Adapter<A: Adapter.Holder, B:Any, C:Any>()
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // --------------------------------------------------
    protected var contentList = arrayListOf<Item<A, B>>()

    // --------------------------------------------------
    private var recyclerView : RecyclerView? = null
    protected abstract var onBindView : (Int) -> Unit

    // --------------------------------------------------
    override fun getItemCount() : Int = contentList.size
    override fun getItemViewType(position:Int) : Int { return position }
    override fun onCreateViewHolder(parent: ViewGroup, position:Int) : RecyclerView.ViewHolder { return contentList[position].holder }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position:Int){ onBindView(position) }

    // --------------------------------------------------
    data class Item<A,B>(var holder:A, var data:B)
    abstract class Holder(context:Context, layout:Int) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layout, null, true)){

        // ----------------------------------------------
        protected fun <T:View?> find(id:Int):T = itemView.findViewById<T>(id)

    }

}