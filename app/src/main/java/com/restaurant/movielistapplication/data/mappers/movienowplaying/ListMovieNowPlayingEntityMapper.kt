package com.restaurant.movielistapplication.data.mappers.movienowplaying

import com.restaurant.movielistapplication.data.mappers.MapperData
import com.restaurant.movielistapplication.data.mappers.MovieDetailsMapper
import com.restaurant.movielistapplication.data.storage.models.movienowplaying.ListMovieNowPlayingEntity
import com.restaurant.movielistapplication.domain.models.movienowplaying.ListMovieNowPlaying

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieNowPlayingEntityMapper : MapperData<ListMovieNowPlayingEntity, ListMovieNowPlaying> {

    override fun mapFromEntity(type: ListMovieNowPlayingEntity): ListMovieNowPlaying {
        return ListMovieNowPlaying(
            page = type.page,
            results = MovieDetailsMapper().mapFromEntity(type.results),
            totalPages = type.totalPages,
            totalResults = type.totalResults
        )
    }
}