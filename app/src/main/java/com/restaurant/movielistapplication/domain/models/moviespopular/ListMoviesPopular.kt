package com.restaurant.movielistapplication.domain.models.moviespopular

import com.restaurant.movielistapplication.domain.models.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMoviesPopular(
    val page: Int? = null,
    val moviesPopularDetails: List<MovieDetails>,
    val total_pages: Int? = null,
    val total_results: Int? = null
)