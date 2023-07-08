package com.restaurant.movielistapplication.data.storage.themoviedb

import com.restaurant.movielistapplication.data.storage.model.ListMovieSectionsEntity
import com.restaurant.movielistapplication.utils.AppConstant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MovieApiService {
    @GET(AppConstant.SECTION_ENDPOINT)
    fun getListMovieSections(
        @Path("section_name") sectionName: String,
        @Query("api_key") key: String
    ): Call<ListMovieSectionsEntity>
}

