package com.yassir.show.activity

import com.yassir.show.R
import com.yassir.show.Api
import android.content.Intent
import com.yassir.show.utils.e
import com.yassir.show.model.Genre
import com.yassir.show.utils.onClick
import com.yassir.show.utils.Activity
import android.annotation.SuppressLint
import com.yassir.show.utils.linearLayout
import com.yassir.show.adapter.AdapterGenre
import com.yassir.show.adapter.AdapterMovie
import androidx.recyclerview.widget.RecyclerView
import com.yassir.show.sheet.SheetDemo
import kotlinx.android.synthetic.main.activity_home.*

class ActivityHome : Activity(R.layout.activity_home) {

    @SuppressLint("SetTextI18n")
    override fun onCreate() {
        super.onCreate()
        _search.onClick { SheetDemo(this).onCreate() }
        _more_top_rated.onClick {
            startActivity(Intent(this, ActivityMore::class.java).apply { putExtra("title", "Top Rated") })
        }
        _more_upcoming.onClick {
            startActivity(Intent(this, ActivityMore::class.java).apply { putExtra("title", "Up Coming") })
        }
        _genres.run {
            linearLayout(RecyclerView.HORIZONTAL)
            adapter = AdapterGenre(this@ActivityHome, Genre(-1))
            Api.genres {
                e("EEEEEEEEEE -- " + it.size)
                adapter = AdapterGenre(this@ActivityHome, *it.toTypedArray()){
                    _popular.run {
                        linearLayout(RecyclerView.HORIZONTAL)
                        adapter = AdapterMovie(this@ActivityHome)
                        Api.popular { list -> adapter = AdapterMovie(this@ActivityHome, *list.toTypedArray()) }
                    }
                    _top_rated.run {
                        linearLayout(RecyclerView.HORIZONTAL)
                        adapter = AdapterMovie(this@ActivityHome)
                        Api.topRated { list -> adapter = AdapterMovie(this@ActivityHome, *list.toTypedArray()) }
                    }
                    _upcoming.run {
                        linearLayout(RecyclerView.HORIZONTAL)
                        adapter = AdapterMovie(this@ActivityHome)
                        Api.upcoming { list -> adapter = AdapterMovie(this@ActivityHome, *list.toTypedArray()) }
                    }
                }
            }

        }

    }
}