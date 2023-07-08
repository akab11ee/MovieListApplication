package com.restaurant.movielistapplication.data.storage.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class ListMovieSectionsEntity(
    @SerializedName("dates") val dates: DateEntity? = DateEntity(),
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<MovieDetailsEntity> = arrayListOf(),
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null
)