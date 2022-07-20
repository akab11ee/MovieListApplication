package com.restaurant.movielistapplication.domain.repository

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movienowplaying.ListMovieNowPlaying
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopular
import com.restaurant.movielistapplication.domain.models.movietoprated.ListMovieTopRated
import com.restaurant.movielistapplication.domain.models.movieupcoming.ListMovieUpcoming
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MoviesRepository {
    suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopular>>

    suspend fun getMoviesNowPlaying(): Flow<Response<ListMovieNowPlaying>>

    suspend fun getMoviesTopRated(): Flow<Response<ListMovieTopRated>>

    suspend fun getMoviesUpcoming(): Flow<Response<ListMovieUpcoming>>

}