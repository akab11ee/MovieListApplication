package com.restaurant.movielistapplication.data.storage.models.movienowplaying

import com.google.gson.annotations.SerializedName
import com.restaurant.movielistapplication.data.storage.models.DateEntity
import com.restaurant.movielistapplication.data.storage.models.MovieDetailsEntity

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMovieNowPlayingEntity(
    @SerializedName("dates") val dates: DateEntity? = DateEntity(),
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<MovieDetailsEntity> = arrayListOf(),
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null
)