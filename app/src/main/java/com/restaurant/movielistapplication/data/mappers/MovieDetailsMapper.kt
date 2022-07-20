package com.restaurant.movielistapplication.data.mappers

import com.restaurant.movielistapplication.data.storage.models.MovieDetailsEntity
import com.restaurant.movielistapplication.domain.models.MovieDetails
import com.restaurant.movielistapplication.utils.AppConstant

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MovieDetailsMapper : MapperData<List<MovieDetailsEntity>, List<MovieDetails>> {

    override fun mapFromEntity(type: List<MovieDetailsEntity>): List<MovieDetails> {
        val listResult = ArrayList<MovieDetails>()

        type.forEach { resultEntity ->
            listResult.add(
                MovieDetails(
                    adult = resultEntity.adult,
                    backdropPath = resultEntity.backdropPath,
                    id = resultEntity.id,
                    originalLanguage = resultEntity.originalLanguage,
                    originalTitle = resultEntity.originalTitle,
                    overview = resultEntity.overview,
                    popularity = resultEntity.popularity,
                    posterPath = AppConstant.MOVIE_BASE_IMAGE_URL + resultEntity.posterPath,
                    releaseDate = resultEntity.releaseDate,
                    title = resultEntity.title?.replace("//s+", "\n"),
                    video = resultEntity.video,
                    voteAverage = resultEntity.voteAverage,
                    voteCount = resultEntity.voteCount
                )
            )
        }

        return listResult.toList()
    }
}