package com.yassir.show.activity

import android.view.View
import com.yassir.show.R
import com.yassir.show.Api
import com.yassir.show.utils.*
import com.yassir.show.adapter.AdapterMovie
import com.yassir.show.model.Movie
import com.yassir.show.sheet.SheetDemo
import kotlinx.android.synthetic.main.activity_more.*

class ActivityMore : Activity(R.layout.activity_more) {

    override fun onCreate() {
        super.onCreate()
        _back.onClick { finish() }
        _search.onClick { SheetDemo(this).onCreate() }
        _more.visibility = View.GONE
        _more.onClick { movies() }
        val title = intent.extras?.getString("title") ?: "???"
        _title.text = title
        _movies.gridLayout((screenSize().first / dp2px(170)).toInt())
        movies()
    }
    private var page = 1
    private fun movies(){
        _loading.visibility = View.VISIBLE
        _more.visibility = View.GONE
        when(_title.text){
            "Top Rated" -> { Api.topRated(page) { adapter(it) } }
            "Up Coming" -> { Api.upcoming(page) { adapter(it) } }
        }
    }
    private fun adapter(list:List<Movie>){
        _loading.visibility = View.GONE
        _more.visibility = View.VISIBLE
        if (page == 1) _movies.adapter = AdapterMovie(this, *list.toTypedArray())
        else (_movies.adapter as AdapterMovie).add(*list.toTypedArray())
        if (list.isEmpty()) _more.visibility = View.GONE
        page++
    }

}