package com.restaurant.movielistapplication.data.storage.models

import com.google.gson.annotations.SerializedName

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

data class DateEntity(
    @SerializedName("maximum") val maximum: String? = null,
    @SerializedName("minimum") val minimum: String? = null
)