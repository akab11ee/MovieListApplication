package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    fun getListPopularMovies(
        @Query("api_key") key: String
    ): Call<ListMoviesPopularEntity>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") key: String
    ): Call<ListMoviesPopularEntity>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") key: String
    ): Call<ListMoviesPopularEntity>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") key: String
    ): Call<ListMoviesPopularEntity>


}

