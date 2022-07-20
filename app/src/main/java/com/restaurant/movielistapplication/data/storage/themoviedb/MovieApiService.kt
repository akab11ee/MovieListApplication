package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.models.movienowplaying.ListMovieNowPlayingEntity
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.data.storage.models.movietoprated.ListMovieTopRatedEntity
import com.restaurant.movielistapplication.data.storage.models.movieupcoming.ListMovieUpcomingEntity
import com.restaurant.movielistapplication.utils.AppConstant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MovieApiService {
    @GET(AppConstant.POPULAR_MOVIE_ENDPOINT)
    fun getListPopularMovies(
        @Query("api_key") key: String
    ): Call<ListMoviesPopularEntity>

    @GET(AppConstant.NOW_PLAYING_ENDPOINT)
    fun getNowPlayingMovies(
        @Query("api_key") key: String
    ): Call<ListMovieNowPlayingEntity>

    @GET(AppConstant.TOP_RATED_ENDPOINT)
    fun getTopRatedMovies(
        @Query("api_key") key: String
    ): Call<ListMovieTopRatedEntity>

    @GET(AppConstant.UPCOMING_MOVIE_ENDPOINT)
    fun getUpcomingMovies(
        @Query("api_key") key: String
    ): Call<ListMovieUpcomingEntity>


}

