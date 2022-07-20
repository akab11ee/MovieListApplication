package com.restaurant.movielistapplication.data.repository

import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.getNowPlayingMovieEntity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MoviesRepositoryImplTest {
    private lateinit var moviesStorage: MoviesStorage

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    @Before
    fun setUp() {
        moviesStorage = mockk(relaxUnitFun = true)
        moviesRepositoryImpl = MoviesRepositoryImpl(moviesStorage)
    }

    @Test
    fun `success response gives Response Success data`() {
        // Arrange
        val movieNowPlayingEntity = getNowPlayingMovieEntity()
        every {
            runBlocking {
                moviesStorage.getMoviesNowPlaying()
            }
        } returns flow { emit(Response.Success(movieNowPlayingEntity)) }

        // Act
        runBlocking {
            moviesRepositoryImpl.getMoviesNowPlaying().collect {
                assertEquals(it is Response.Success, true)
            }

        }
    }

    @Test
    fun `error response gives Response Error data`() {
        // Arrange
        every {
            runBlocking {
                moviesStorage.getMoviesNowPlaying()
            }
        } returns flow { emit(Response.Fail(NullPointerException())) }

        // Act
        runBlocking {
            moviesRepositoryImpl.getMoviesNowPlaying().collect {
                assertEquals(it is Response.Fail, true)
            }

        }
    }
}




