package com.restaurant.movielistapplication.data.mappers.movieupcoming

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.mappers.MovieDetailsMapper
import com.restaurant.movielistapplication.data.storage.models.movieupcoming.ListMovieUpcomingEntity
import com.restaurant.movielistapplication.domain.models.movieupcoming.ListMovieUpcoming

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieUpcomingEntityMapper : MapperData<ListMovieUpcomingEntity, ListMovieUpcoming> {

    override fun mapFromEntity(type: ListMovieUpcomingEntity): ListMovieUpcoming {
        return ListMovieUpcoming(
            page = type.page,
            results = MovieDetailsMapper().mapFromEntity(type.results),
            totalPages = type.totalPages,
            totalResults = type.totalResults
        )
    }
}