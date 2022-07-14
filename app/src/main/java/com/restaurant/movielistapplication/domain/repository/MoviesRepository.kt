package com.restaurant.movielistapplication.domain.repository

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopularD
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularD>>
}