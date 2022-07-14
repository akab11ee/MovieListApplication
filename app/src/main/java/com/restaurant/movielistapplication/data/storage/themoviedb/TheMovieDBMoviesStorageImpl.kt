package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.utils.AppConstant
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback

class TheMovieDBMoviesStorageImpl(private val movieDBApiInterface: MovieApiService) :
    MoviesStorage {

    override suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieDBApiInterface.getListPopularMovies(
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
                        trySend(Response.Fail(e = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }
}