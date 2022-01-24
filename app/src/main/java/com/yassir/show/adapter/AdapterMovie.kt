package com.yassir.show.adapter

import android.view.View
import com.yassir.show.R
import com.yassir.show.Api
import android.content.Intent
import android.content.Context
import android.widget.TextView
import android.widget.ImageView
import com.yassir.show.model.Movie
import com.yassir.show.utils.Adapter
import com.yassir.show.utils.onClick
import android.annotation.SuppressLint
import com.yassir.show.utils.loadImage
import androidx.cardview.widget.CardView
import com.yassir.show.activity.ActivityMovie


class AdapterMovie(val context:Context, vararg movies:Movie)
    : Adapter<Adapter.Holder, Movie, String>() {

    init {
        movies.map { contentList.add(Item(HolderMovie(context), it)) }
        if (movies.isEmpty()) contentList.add(Item(HolderLoad(context), Movie()))
    }

    // --------------------------------------------------
    @SuppressLint("NotifyDataSetChanged")
    fun add(vararg movies:Movie){
        movies.map { contentList.add(Item(HolderMovie(context), it)) }
        contentList.firstOrNull()?.run {
            if (this.data.id < 0) contentList.removeAt(0)
        }
        notifyDataSetChanged()
    }
    override var onBindView: (Int) -> Unit = { position ->
        val (holder, item) = contentList[position]
        when(holder){
            is HolderLoad -> { }
            is HolderMovie -> holder(item) {
                val intent = Intent(context, ActivityMovie::class.java)
                intent.putExtra("movie_id", item.id)
                context.startActivity(intent)
            }
        }
    }

    // ==================================================
    class HolderMovie(context:Context) : Adapter.Holder(context, R.layout.holder_movie) {
        // --------------------------------------------------
        private val _adult = find<CardView>(R.id._adult)
        private val _poster = find<ImageView>(R.id._poster)
        private val _title = find<TextView>(R.id._title)
        private val _vote_average = find<TextView>(R.id._vote_average)
        private val _release_date = find<TextView>(R.id._release_date)
        // --------------------------------------------------
        operator fun invoke(movie:Movie, callback:() -> Unit) {
            if (movie.adult) _adult.visibility = View.VISIBLE else _adult.visibility = View.GONE
            movie.poster_path.run {
                _poster.loadImage(if(this != null ) Api.image(this) else R.drawable.default_poster)
            }
            _title.text = movie.title
            _vote_average.text = movie.vote_average.toString()
            _release_date.text = movie.release_date.take(4)
            itemView.onClick() {
                return@onClick callback()
            }
        }
    }
    class HolderLoad(context:Context) : Adapter.Holder(context, R.layout.holder_load) {
        // --------------------------------------------------
        operator fun invoke(callback:() -> Unit) {


        }
    }
}