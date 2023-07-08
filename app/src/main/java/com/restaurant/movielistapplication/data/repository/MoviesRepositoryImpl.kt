package com.restaurant.movielistapplication.data.repository

import com.restaurant.movielistapplication.data.mappers.moviesection.ListMovieSectionsEntityMapper
import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviesection.ListMovieSections
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MoviesRepositoryImpl(private val moviesStorage: MoviesStorage) : MoviesRepository {

    override suspend fun getMovieSections(sectionName: String): Flow<Response<ListMovieSections>> {
        return moviesStorage.getMovieSections(sectionName).transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(exception = response.exception))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMovieSectionsEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }
}