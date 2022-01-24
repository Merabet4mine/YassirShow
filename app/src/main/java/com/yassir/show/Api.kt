package com.yassir.show

import com.github.kittinunf.fuel.Fuel
import com.yassir.show.model.*
import com.yassir.show.utils.convert
import com.yassir.show.utils.e
import com.yassir.show.utils.fromJson
import com.yassir.show.utils.runOnUiThread
import kotlin.concurrent.thread
import kotlin.math.min

object Api {

    // ----------------------------------------
    private const val apiKey = "<API_KEY>"
    private const val apiUrl = "https://api.themoviedb.org"
    private const val apiVersion = "3"

    // ----------------------------------------
    fun image(name:String, size:Int=200) = "https://image.tmdb.org/t/p/w${min(size, 500)}$name"

    fun genres(callback:(List<Genre>) -> Unit) {
        "genre/movie/list".GET<Map<String,ArrayList<*>>> {
            if (it != null) callback(it["genres"]!!.map { it.convert<Genre>()!! })
        }
    }
    fun details(movie:Movie, callback:(Movie) -> Unit) {
        "movie/${movie.id}".GET<Movie>{ it?.apply { callback(this) } }
    }
    fun popular(page:Int=1, callback:(List<Movie>) -> Unit) {
        "movie/popular".GET<Map<String,*>>("page" to page){ map ->
            map?.apply { callback((map["results"] as ArrayList<*>).map { it.convert()!! }) }
        }
    }
    fun topRated(page:Int=1, callback:(List<Movie>) -> Unit) {
        "movie/top_rated".GET<Map<String,*>>("page" to page){ map ->
            map?.apply { callback((map["results"] as ArrayList<*>).map { it.convert()!! }) }
        }
    }
    fun upcoming(page:Int=1, callback:(List<Movie>) -> Unit) {
        "movie/upcoming".GET<Map<String,*>>("page" to page){ map ->
            map?.apply { callback((map["results"] as ArrayList<*>).map { it.convert()!! }) }
        }
    }
    fun casts(movie:Movie, callback:(List<Cast>) -> Unit) {
        "movie/${movie.id}/credits".GET<Map<String,*>> { map ->
            map?.apply { callback((this["cast"] as ArrayList<*>).map { it.convert()!! }) }
        }
    }

    fun recommendations(movie:Movie, page:Int=1, callback:(List<Movie>) -> Unit) {
        "movie/${movie.id}/recommendations".GET<Map<String,*>>("page" to page){ map ->
            map?.apply { callback((map["results"] as ArrayList<*>).map { it.convert()!! }) }
        }
    }


    private inline
    fun <reified T> String.GET (
        vararg query:Pair<String, Any>,
        crossinline callback:(T?) -> Unit
    ) = thread {
        Fuel.get("$apiUrl/$apiVersion/$this", listOf("api_key" to apiKey) + query)
            .response { request, response, result ->
                when(response.statusCode){
                    200 -> {
                        val data = result.get().decodeToString().fromJson<T>()
                        runOnUiThread { callback(data) }
                    }
                    401 -> {
                        // TODO : ...
                    }
                    404 -> {
                        // TODO : ...
                    }
                }
            }
    }

}