package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movieupcoming.ListMovieUpcoming
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class GetListMovieUpcomingUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Flow<Response<ListMovieUpcoming>> {
        delay(1000)
        return moviesRepository.getMoviesUpcoming()
    }
}