package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopularD
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetListMoviesPopularUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(): Flow<Response<ListMoviesPopularD>> {
        return moviesRepository.getMoviesPopular()
    }
}