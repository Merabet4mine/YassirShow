package com.yassir.show.adapter

import com.yassir.show.R
import com.yassir.show.Api
import android.content.Context
import android.widget.TextView
import android.widget.ImageView
import com.yassir.show.model.Cast
import com.yassir.show.utils.Adapter
import android.annotation.SuppressLint
import com.yassir.show.utils.loadImage

@SuppressLint("NotifyDataSetChanged")
class AdapterCast(val context:Context, vararg casts: Cast)
    : Adapter<Adapter.Holder, Cast, String>() {

    // --------------------------------------------------
    init {
        contentList.addAll(casts.map {
            if (it.id < 0) Item(HolderLoad(context), it)
            else Item(HolderCast(context), it)
        })
    }

    // --------------------------------------------------
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        when(holder){
            is HolderLoad -> {}
            is HolderCast -> { holder(item) }
        }
    }

    // HOLDERS ==========================================
    class HolderCast(context:Context) : Adapter.Holder(context, R.layout.holder_cast) {
        // --------------------------------------------------
        private val _profile_path = find<ImageView>(R.id._profile_path)
        private val _name = find<TextView>(R.id._name)
        private val _character = find<TextView>(R.id._character)
        // --------------------------------------------------
        operator fun invoke(cast:Cast, callback:() -> Unit = {}) {
            _profile_path.loadImage(Api.image(cast.profile_path ?: ""), R.drawable.default_poster)
            _name.text = cast.name
            _character.text = cast.character
        }
    }
    class HolderLoad(context:Context) : Adapter.Holder(context, R.layout.holder_load)
}