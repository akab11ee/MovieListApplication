package com.restaurant.movielistapplication.presentation.state

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class MovieState(
    var isNowPlayingExpanded: Boolean = true,
    var isPopularMovieExpanded: Boolean = true,
    var isTopRatedMovieExpanded: Boolean = false,
    var isUpcomingMovieExpanded: Boolean = false
)