package com.restaurant.movielistapplication.presentation.interfaces

import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.domain.models.moviesection.MovieDetails

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface OnParentItemClickListener {
    fun onItemClick(movie: Movie)
    fun onMovieClick(movie: MovieDetails)
}