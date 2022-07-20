package com.restaurant.movielistapplication.data.mappers

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

interface MapperData<E, D> {
    fun mapFromEntity(type: E): D
}