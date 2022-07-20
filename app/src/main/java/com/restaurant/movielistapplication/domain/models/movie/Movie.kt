package com.restaurant.movielistapplication.domain.models.movie

import com.restaurant.movielistapplication.domain.models.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class Movie(
    var isExpanded: Boolean = false,
    val sectionName: String = "",
    val movieDetailList: List<MovieDetails>? = null
)
