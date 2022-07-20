package com.restaurant.movielistapplication.data.mappers.movienowplaying

import com.restaurant.movielistapplication.getNowPlayingMovieEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieNowPlayingEntityMapperTest {

    private lateinit var listMovieNowPlayingEntityMapper: ListMovieNowPlayingEntityMapper

    @Before
    fun setUp() {
        listMovieNowPlayingEntityMapper = ListMovieNowPlayingEntityMapper()
    }

    @Test
    fun `mapping function called result remains same`() {

        // Arrange
        val nowPlayingEntity = getNowPlayingMovieEntity()

        // Act
        val result = listMovieNowPlayingEntityMapper.mapFromEntity(nowPlayingEntity)

        // Assert
        assertEquals(nowPlayingEntity.page, result.page)
        assertEquals(nowPlayingEntity.totalPages, result.totalPages)
        assertEquals(nowPlayingEntity.totalResults, result.totalResults)
        assertEquals(nowPlayingEntity.results.size, result.results.size)
    }

}