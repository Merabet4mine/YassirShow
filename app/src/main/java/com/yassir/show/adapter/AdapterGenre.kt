package com.yassir.show.adapter

import com.yassir.show.R
import android.content.Context
import android.widget.TextView
import com.yassir.show.model.Genre
import com.yassir.show.utils.color
import com.yassir.show.utils.Adapter
import com.yassir.show.utils.onClick
import android.annotation.SuppressLint
import androidx.cardview.widget.CardView
import com.daimajia.androidanimations.library.Techniques

@SuppressLint("NotifyDataSetChanged")
class AdapterGenre(val context:Context, vararg genres:Genre, onSelectItem:(Genre)->Unit={}) : Adapter<AdapterGenre.Holder, Genre, String>() {

    // --------------------------------------------------
    init {
        genres.forEachIndexed { index, genre ->
            contentList.add(Item(Holder(context), genre.apply { selected = index == 0 }))
        }
        genres.firstOrNull()?.apply { if (id > 0) onSelectItem(this) }
    }

    // --------------------------------------------------
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        if (item.id >= 0) holder(item) {
            contentList.forEachIndexed { index, item -> item.data.selected = index == position }
            notifyDataSetChanged()
            onSelectItem(item)
        }

    }

    // HOLDERS ==========================================
    class Holder(context:Context) : Adapter.Holder(context, R.layout.holder_genre) {
        // --------------------------------------------------
        private val _card = find<CardView>(R.id._card)
        private val _name = find<TextView>(R.id._name)
        // --------------------------------------------------
        operator fun invoke(genre:Genre, callback:() -> Unit) {
            _name.text = genre.name
            fun selected() {
                _card.setCardBackgroundColor(_card.context.color(if(genre.selected) R.color.blue_700 else R.color.white_00))
                _name.setTextColor(_name.context.color(if(genre.selected) R.color.white_100 else R.color.blue_700))
            }
            if (genre.id > 0) itemView.onClick(Techniques.FlipInX) {
                genre.selected = !genre.selected
                return@onClick callback()
            }
            selected()
        }
    }
}