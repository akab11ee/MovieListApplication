package com.restaurant.movielistapplication.data.storage.models.moviespopular

import com.google.gson.annotations.SerializedName
import com.restaurant.movielistapplication.data.storage.models.MovieDetailsEntity

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMoviesPopularEntity(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<MovieDetailsEntity> = arrayListOf(),
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null
)