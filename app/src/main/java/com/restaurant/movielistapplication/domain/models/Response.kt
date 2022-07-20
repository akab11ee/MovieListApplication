package com.restaurant.movielistapplication.domain.models

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

sealed class Response<out T> {
    class Loading<out T> : Response<T>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Fail<out T>(val exception: Exception) : Response<T>()
}