package com.restaurant.movielistapplication.data.mappers

interface MapperData<E, D> {

    fun mapFromEntity(type: E): D
}