package com.yassir.show.activity

import android.view.View
import com.yassir.show.R
import com.yassir.show.Api
import com.yassir.show.model.Cast
import com.yassir.show.model.Movie
import com.yassir.show.utils.onClick
import com.yassir.show.utils.Activity
import android.annotation.SuppressLint
import com.yassir.show.utils.loadImage
import com.yassir.show.utils.linearLayout
import com.yassir.show.adapter.AdapterCast
import com.yassir.show.adapter.AdapterSmall
import androidx.recyclerview.widget.RecyclerView
import com.yassir.show.adapter.AdapterMovie
import com.yassir.show.sheet.SheetDemo
import kotlinx.android.synthetic.main.activity_movie.*

class ActivityMovie : Activity(R.layout.activity_movie) {

    @SuppressLint("SetTextI18n")
    override fun onCreate() {
        super.onCreate()
        val movie_id = intent.extras!!.getInt("movie_id")
        _back.onClick { finish() }
        Api.details(Movie(id = movie_id)) { movie ->
            if (!movie.video) _play.visibility = View.GONE
            else _play.onClick { SheetDemo(this).onCreate() }
            _poster.loadImage(Api.image(movie.poster_path ?: "", 500), R.drawable.default_poster)
            _spoken_languages.run {
                linearLayout(RecyclerView.HORIZONTAL)
                adapter = AdapterSmall(this@ActivityMovie, movie.spoken_languages.map { it.name })
            }
            _title.text = movie.title
            if (!movie.adult) _adult.visibility = View.GONE
            _release_date.text = movie.release_date
            _runtime.text = "${(movie.runtime ?: 0)/60}h ${(movie.runtime ?: 0)%60}m"
            _vote_average.text = movie.vote_average.toString()
            _vote_count.text = "(${movie.vote_count})"
            _genres.run {
                linearLayout(RecyclerView.HORIZONTAL)
                adapter = AdapterSmall(this@ActivityMovie, movie.genres.map { it.name }, true)
            }
            _overview.text = movie.overview
            _cast.run {
                linearLayout(RecyclerView.HORIZONTAL)
                adapter = AdapterCast(this@ActivityMovie, Cast(-1))
                Api.casts(movie){ list -> adapter = AdapterCast(this@ActivityMovie, *list.toTypedArray()) }
            }
            _recommendations.run {
                linearLayout(RecyclerView.HORIZONTAL)
                adapter = AdapterMovie(this@ActivityMovie, Movie(id = -1))
                Api.recommendations(movie){ list -> adapter = AdapterMovie(this@ActivityMovie, *list.toTypedArray()) }
            }

            production_companies.text = movie.production_companies.map { it.name }.joinToString("  •  ")
        }

    }
}