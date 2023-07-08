package com.restaurant.movielistapplication.data.mappers.moviesection

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.storage.model.ListMovieSectionsEntity
import com.restaurant.movielistapplication.domain.models.moviesection.ListMovieSections

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieSectionsEntityMapper : MapperData<ListMovieSectionsEntity, ListMovieSections> {

    override fun mapFromEntity(type: ListMovieSectionsEntity): ListMovieSections {
        return ListMovieSections(
            page = type.page,
            results = MovieDetailsMapper().mapFromEntity(type.results),
            totalPages = type.totalPages,
            totalResults = type.totalResults
        )
    }
}