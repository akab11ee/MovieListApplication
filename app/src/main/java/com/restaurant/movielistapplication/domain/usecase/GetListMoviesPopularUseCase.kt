package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopular
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class GetListMoviesPopularUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Flow<Response<ListMoviesPopular>> {
        delay(1000)
        return moviesRepository.getMoviesPopular()
    }
}