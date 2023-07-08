package com.restaurant.movielistapplication.data.storage

import com.restaurant.movielistapplication.data.storage.model.ListMovieSectionsEntity
import com.restaurant.movielistapplication.domain.models.Response
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MoviesStorage {
    suspend fun getMovieSections(sectionName: String): Flow<Response<ListMovieSectionsEntity>>

}