package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.data.storage.model.ListMovieSectionsEntity
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.utils.AppConstant
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

class MovieApiServiceImpl(private val movieApiService: MovieApiService) :
    MoviesStorage {

    override suspend fun getMovieSections(sectionName: String): Flow<Response<ListMovieSectionsEntity>> =
        callbackFlow {
            trySend(Response.Loading())

            movieApiService.getListMovieSections(
                sectionName = sectionName,
                key = AppConstant.MOVIE_DB_KEY,
            )
                .enqueue(object : Callback<ListMovieSectionsEntity> {

                    override fun onResponse(
                        call: Call<ListMovieSectionsEntity>,
                        response: retrofit2.Response<ListMovieSectionsEntity>
                    ) {
                        response.body().let {
                            if (it != null) trySend(Response.Success(data = it))
                        }
                    }

                    override fun onFailure(call: Call<ListMovieSectionsEntity>, t: Throwable) {
                        trySend(Response.Fail(exception = t as Exception))
                    }
                })

            awaitClose { this.cancel() }
        }
}