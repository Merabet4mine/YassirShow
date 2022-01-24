package com.yassir.show.model

data class Movie
(
    var adult : Boolean = false,
    var backdrop_path : String? = null,
    var budget : Int = 0,
    var genres : ArrayList<Genre> = arrayListOf(),
    var homepage : String? = null,
    var id : Int = 0,
    var imdb_id : String? = null,
    var original_language : String = "",
    var original_title : String = "",
    var overview : String? = null,
    var popularity : Number = 0,
    var poster_path : String? = null,
    var production_companies : ArrayList<Company> = arrayListOf(),
    var production_countries : ArrayList<Country> = arrayListOf(),
    var release_date : String = "",
    var revenue : Int = 0,
    var runtime : Int? = null,
    var spoken_languages : ArrayList<Language> = arrayListOf(),
    var status : String = "",
    var tagline : String? = null,
    var title : String = "",
    var video : Boolean = false,
    var vote_average : Number = 0.0,
    var vote_count : Int = 0,
)