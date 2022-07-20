package com.restaurant.movielistapplication.presentation.interfaces

import com.restaurant.movielistapplication.domain.models.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface OnChildItemClickListener {
    fun onMovieClick(movieDetails: MovieDetails)
}