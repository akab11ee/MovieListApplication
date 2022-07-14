package com.restaurant.movielistapplication.data.storage.models.moviespopular

data class ListMoviesPopularEntity(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)