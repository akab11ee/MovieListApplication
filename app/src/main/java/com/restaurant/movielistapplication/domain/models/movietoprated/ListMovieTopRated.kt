package com.restaurant.movielistapplication.domain.models.movietoprated

import com.restaurant.movielistapplication.domain.models.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMovieTopRated(
    val page: Int? = null,
    val results: List<MovieDetails> = arrayListOf(),
    val totalPages: Int? = null,
    val totalResults: Int? = null
)