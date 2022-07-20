package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movietoprated.ListMovieTopRated
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class GetListMovieTopRatedUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Flow<Response<ListMovieTopRated>> {
        delay(1000)
        return moviesRepository.getMoviesTopRated()
    }
}