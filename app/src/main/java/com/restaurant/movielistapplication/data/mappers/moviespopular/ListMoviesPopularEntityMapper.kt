package com.restaurant.movielistapplication.data.mappers.moviespopular

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.storage.models.moviespopular.ListMoviesPopularEntity
import com.restaurant.movielistapplication.domain.models.moviespopular.ListMoviesPopularD

class ListMoviesPopularEntityMapper : MapperData<ListMoviesPopularEntity, ListMoviesPopularD> {

    override fun mapFromEntity(type: ListMoviesPopularEntity): ListMoviesPopularD {
        return ListMoviesPopularD(
            page = type.page,
            moviesPopularDetails = ResultListMoviesEntityMapper().mapFromEntity(type.results),
            total_pages = type.total_pages,
            total_results = type.total_results
        )
    }
}