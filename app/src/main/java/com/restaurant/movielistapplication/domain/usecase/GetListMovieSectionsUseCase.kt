package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviesection.ListMovieSections
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class GetListMovieSectionsUseCase(private val moviesRepository: MoviesRepository) {
    suspend fun execute(sectionName: String): Flow<Response<ListMovieSections>> {
        return moviesRepository.getMovieSections(sectionName)
    }
}