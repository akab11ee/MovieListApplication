package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.data.storage.models.movienowplaying.ListMovieNowPlayingEntity
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.data.storage.models.movietoprated.ListMovieTopRatedEntity
import com.restaurant.movielistapplication.data.storage.models.movieupcoming.ListMovieUpcomingEntity
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.utils.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@OptIn(ExperimentalCoroutinesApi::class)
class MovieApiServiceImpl(private val movieApiService: MovieApiService) :
    MoviesStorage {

    override suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieApiService.getListPopularMovies(
                key = AppConstant.MOVIE_DB_KEY,
            )
                .enqueue(object : Callback<ListMoviesPopularEntity> {

                    override fun onResponse(
                        call: Call<ListMoviesPopularEntity>,
                        response: retrofit2.Response<ListMoviesPopularEntity>
                    ) {
                        response.body().let {
                            if (it != null) trySend(Response.Success(data = it))
                        }
                    }

                    override fun onFailure(call: Call<ListMoviesPopularEntity>, t: Throwable) {
                        trySend(Response.Fail(exception = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }

    override suspend fun getMoviesNowPlaying(): Flow<Response<ListMovieNowPlayingEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieApiService.getNowPlayingMovies(
                key = AppConstant.MOVIE_DB_KEY,
            )
                .enqueue(object : Callback<ListMovieNowPlayingEntity> {

                    override fun onResponse(
                        call: Call<ListMovieNowPlayingEntity>,
                        response: retrofit2.Response<ListMovieNowPlayingEntity>
                    ) {
                        response.body().let {
                            if (it != null) trySend(Response.Success(data = it))
                        }
                    }

                    override fun onFailure(call: Call<ListMovieNowPlayingEntity>, t: Throwable) {
                        trySend(Response.Fail(exception = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }

    override suspend fun getMoviesTopRated(): Flow<Response<ListMovieTopRatedEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieApiService.getTopRatedMovies(
                key = AppConstant.MOVIE_DB_KEY,
            )
                .enqueue(object : Callback<ListMovieTopRatedEntity> {

                    override fun onResponse(
                        call: Call<ListMovieTopRatedEntity>,
                        response: retrofit2.Response<ListMovieTopRatedEntity>
                    ) {
                        response.body().let {
                            if (it != null) trySend(Response.Success(data = it))
                        }
                    }

                    override fun onFailure(call: Call<ListMovieTopRatedEntity>, t: Throwable) {
                        trySend(Response.Fail(exception = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }

    override suspend fun getMoviesUpcoming(): Flow<Response<ListMovieUpcomingEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieApiService.getUpcomingMovies(
                key = AppConstant.MOVIE_DB_KEY,
            )
                .enqueue(object : Callback<ListMovieUpcomingEntity> {

                    override fun onResponse(
                        call: Call<ListMovieUpcomingEntity>,
                        response: retrofit2.Response<ListMovieUpcomingEntity>
                    ) {
                        response.body().let {
                            if (it != null) trySend(Response.Success(data = it))
                        }
                    }

                    override fun onFailure(call: Call<ListMovieUpcomingEntity>, t: Throwable) {
                        trySend(Response.Fail(exception = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }
}