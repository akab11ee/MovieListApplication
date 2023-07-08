package com.restaurant.movielistapplication.domain.repository

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviesection.ListMovieSections
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MoviesRepository {
    suspend fun getMovieSections(sectionName: String): Flow<Response<ListMovieSections>>

}