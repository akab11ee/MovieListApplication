package com.restaurant.movielistapplication.data.storage

import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface MoviesStorage {
    suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularEntity>>
}