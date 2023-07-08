package com.restaurant.movielistapplication.data.mappers.moviesection

import com.restaurant.movielistapplication.getMovieSectionsEntityResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class ListMovieSectionsEntityMapperTest {

    private lateinit var listMovieSectionEntityMapper: ListMovieSectionsEntityMapper

    @Before
    fun setUp() {
        listMovieSectionEntityMapper = ListMovieSectionsEntityMapper()
    }

    @Test
    fun `mapping function called result remains same`() {

        // Arrange
        val nowPlayingEntity = getMovieSectionsEntityResponse()

        // Act
        val result = listMovieSectionEntityMapper.mapFromEntity(nowPlayingEntity)

        // Assert
        Assert.assertEquals(nowPlayingEntity.page, result.page)
        Assert.assertEquals(nowPlayingEntity.totalPages, result.totalPages)
        Assert.assertEquals(nowPlayingEntity.totalResults, result.totalResults)
        Assert.assertEquals(nowPlayingEntity.results.size, result.results.size)
    }

}