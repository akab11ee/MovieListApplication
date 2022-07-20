package com.restaurant.movielistapplication.data.mappers.movietoprated

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.mappers.MovieDetailsMapper
import com.restaurant.movielistapplication.data.storage.models.movietoprated.ListMovieTopRatedEntity
import com.restaurant.movielistapplication.domain.models.movietoprated.ListMovieTopRated

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieTopRatedEntityMapper : MapperData<ListMovieTopRatedEntity, ListMovieTopRated> {

    override fun mapFromEntity(type: ListMovieTopRatedEntity): ListMovieTopRated {
        return ListMovieTopRated(
            page = type.page,
            results = MovieDetailsMapper().mapFromEntity(type.results),
            totalPages = type.totalPages,
            totalResults = type.totalResults
        )
    }
}