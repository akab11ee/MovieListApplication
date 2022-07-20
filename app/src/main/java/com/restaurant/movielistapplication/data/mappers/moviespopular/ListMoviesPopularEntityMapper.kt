package com.restaurant.movielistapplication.data.mappers.moviespopular

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.mappers.MovieDetailsMapper
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopular

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMoviesPopularEntityMapper : MapperData<ListMoviesPopularEntity, ListMoviesPopular> {

    override fun mapFromEntity(type: ListMoviesPopularEntity): ListMoviesPopular {
        return ListMoviesPopular(
            page = type.page,
            moviesPopularDetails = MovieDetailsMapper().mapFromEntity(type.results),
            total_pages = type.totalPages,
            total_results = type.totalResults
        )
    }
}