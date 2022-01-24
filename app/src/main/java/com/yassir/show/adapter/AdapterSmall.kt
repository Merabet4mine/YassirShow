package com.yassir.show.adapter

import com.yassir.show.R
import android.content.Context
import android.widget.TextView
import com.yassir.show.utils.color
import com.yassir.show.utils.Adapter
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils

class AdapterSmall(val context:Context, list:List<String>, val colored:Boolean=false)
    : Adapter<AdapterSmall.HolderSmall, String, Unit>() {

    // --------------------------------------------------
    init { contentList.addAll(list.map { Item(HolderSmall(context), it) }) }

    // --------------------------------------------------
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        holder(item, colored)
    }

    // HOLDERS ==========================================
    class HolderSmall(context:Context) : Adapter.Holder(context, R.layout.holder_small) {
        // --------------------------------------------------
        private val _card = find<CardView>(R.id._card)
        private val _name = find<TextView>(R.id._name)
        // --------------------------------------------------
        operator fun invoke(name:String, colored:Boolean) {
            _name.text = name
            if(colored){
                val colors = arrayListOf(
                    R.color.brown_500,
                    R.color.deep_orange_500,
                    R.color.orange_500,
                    R.color.amber_500,
                    R.color.yellow_500,
                    R.color.lime_500,
                    R.color.light_green_500,
                    R.color.green_500,
                    R.color.teal_500,
                    R.color.cyan_500,
                    R.color.light_blue_500,
                    R.color.blue_500,
                    R.color.indigo_500,
                    R.color.deep_purple_500,
                    R.color.purple_500,
                    R.color.pink_500,
                    R.color.red_500,
                )
                val color = _name.context.color(colors.random())
                _name.setTextColor(color)
                _card.setCardBackgroundColor(ColorUtils.setAlphaComponent(color, 40))
                // _name.setTextColor(color)
                //_card.setCardBackgroundColor(ColorUtils.setAlphaComponent(color, 20))
            }
        }

    }


}