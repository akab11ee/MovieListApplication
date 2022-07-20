package com.restaurant.movielistapplication.data.storage

import com.restaurant.movielistapplication.data.storage.models.movienowplaying.ListMovieNowPlayingEntity
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.data.storage.models.movietoprated.ListMovieTopRatedEntity
import com.restaurant.movielistapplication.data.storage.models.movieupcoming.ListMovieUpcomingEntity
import com.restaurant.movielistapplication.domain.models.Response
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MoviesStorage {
    suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularEntity>>

    suspend fun getMoviesNowPlaying(): Flow<Response<ListMovieNowPlayingEntity>>

    suspend fun getMoviesTopRated(): Flow<Response<ListMovieTopRatedEntity>>

    suspend fun getMoviesUpcoming(): Flow<Response<ListMovieUpcomingEntity>>
}