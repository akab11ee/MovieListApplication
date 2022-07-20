package com.restaurant.movielistapplication.data.repository

import com.restaurant.movielistapplication.data.mappers.movienowplaying.ListMovieNowPlayingEntityMapper
import com.restaurant.movielistapplication.data.mappers.moviespopular.ListMoviesPopularEntityMapper
import com.restaurant.movielistapplication.data.mappers.movietoprated.ListMovieTopRatedEntityMapper
import com.restaurant.movielistapplication.data.mappers.movieupcoming.ListMovieUpcomingEntityMapper
import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movienowplaying.ListMovieNowPlaying
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopular
import com.restaurant.movielistapplication.domain.models.movietoprated.ListMovieTopRated
import com.restaurant.movielistapplication.domain.models.movieupcoming.ListMovieUpcoming
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MoviesRepositoryImpl(private val moviesStorage: MoviesStorage) : MoviesRepository {

    override suspend fun getMoviesPopular(): Flow<Response<ListMoviesPopular>> {
        return moviesStorage.getMoviesPopular().transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(exception = response.exception))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMoviesPopularEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }

    override suspend fun getMoviesNowPlaying(): Flow<Response<ListMovieNowPlaying>> {
        return moviesStorage.getMoviesNowPlaying().transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(exception = response.exception))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMovieNowPlayingEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }

    override suspend fun getMoviesTopRated(): Flow<Response<ListMovieTopRated>> {
        return moviesStorage.getMoviesTopRated().transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(exception = response.exception))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMovieTopRatedEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }

    override suspend fun getMoviesUpcoming(): Flow<Response<ListMovieUpcoming>> {
        return moviesStorage.getMoviesUpcoming().transform { response ->
            when (response) {
                is Response.Loading -> emit(Response.Loading())
                is Response.Fail -> emit(Response.Fail(exception = response.exception))
                is Response.Success -> emit(
                    Response.Success(
                        data = ListMovieUpcomingEntityMapper().mapFromEntity(type = response.data)
                    )
                )
            }
        }
    }
}