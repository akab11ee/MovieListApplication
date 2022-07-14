package com.restaurant.movielistapplication.data.repository

import com.restaurant.movielistapplication.data.mappers.moviespopular.ListMoviesPopularEntityMapper
import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopularD
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class MoviesRepositoryImpl(private val moviesStorage: MoviesStorage) : MoviesRepository {

    override suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopularD>> {
        return moviesStorage.getMoviesPopular().transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(e = response.e))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMoviesPopularEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }
}