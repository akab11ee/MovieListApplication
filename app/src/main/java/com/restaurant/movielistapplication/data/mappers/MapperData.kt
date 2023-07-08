package com.restaurant.movielistapplication.data.mappers

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 *
 * Usually serves as an adapter between domain entities and data transfer entities.
 * This ensures that codebase doesn't have very tight dependencies on the network entities .
 */

interface MapperData<E, D> {
    fun mapFromEntity(type: E): D
}