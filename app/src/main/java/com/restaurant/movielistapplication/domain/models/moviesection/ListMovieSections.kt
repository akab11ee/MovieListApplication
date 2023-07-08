package com.restaurant.movielistapplication.domain.models.moviesection

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */


data class ListMovieSections(
    val dates: Dates? = Dates(),
    val page: Int? = null,
    val results: List<MovieDetails> = arrayListOf(),
    val totalPages: Int? = null,
    val totalResults: Int? = null
)