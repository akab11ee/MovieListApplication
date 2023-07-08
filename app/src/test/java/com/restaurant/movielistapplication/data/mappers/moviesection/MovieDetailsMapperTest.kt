package com.restaurant.movielistapplication.data.mappers.moviesection

import com.restaurant.movielistapplication.getMovieSectionsEntityResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MovieDetailsMapperTest {

    private lateinit var movieDetailsMapper: MovieDetailsMapper

    @Before
    fun setUp() {
        movieDetailsMapper = MovieDetailsMapper()
    }

    @Test
    fun `mapping function called id remains same`() {

        // Arrange
        val nowPlayingEntity = getMovieSectionsEntityResponse().results

        // Act
        val result = movieDetailsMapper.mapFromEntity(nowPlayingEntity)

        // Assert
        assertEquals(nowPlayingEntity[0].id, result[0].id)
    }

    @Test
    fun `mapping function called poster path gives complete image  url`() {

        // Arrange
        val nowPlayingEntity = getMovieSectionsEntityResponse().results
        val completePosterImagePath =
            "https://image.tmdb.org/t/p/w500" + nowPlayingEntity[0].posterPath

        // Act
        val result = movieDetailsMapper.mapFromEntity(nowPlayingEntity)


        // Assert
        assertEquals(result[0].posterPath, completePosterImagePath)
    }

}