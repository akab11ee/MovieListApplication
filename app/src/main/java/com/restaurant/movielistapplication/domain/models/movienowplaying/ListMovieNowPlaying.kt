package com.restaurant.movielistapplication.domain.models.movienowplaying

import com.restaurant.movielistapplication.domain.models.Dates
import com.restaurant.movielistapplication.domain.models.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMovieNowPlaying(
    val dates: Dates? = Dates(),
    val page: Int? = null,
    val results: List<MovieDetails> = arrayListOf(),
    val totalPages: Int? = null,
    val totalResults: Int? = null
)