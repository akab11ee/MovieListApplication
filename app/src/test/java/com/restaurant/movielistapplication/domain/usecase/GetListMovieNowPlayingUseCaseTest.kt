package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import com.restaurant.movielistapplication.getNowPlayingMovieEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class GetListMovieNowPlayingUseCaseTest {
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getListMovieNowPlayingUseCase: GetListMovieNowPlayingUseCase

    @Before
    fun setUp() {
        moviesRepository = mockk(relaxUnitFun = true)
        getListMovieNowPlayingUseCase = GetListMovieNowPlayingUseCase(moviesRepository)
    }

    @Test
    fun `execute method should call repository`() {
        // Arrange
        val movieNowPlayingEntity = getNowPlayingMovieEntity()
        every {
            runBlocking {
                moviesRepository.getMoviesNowPlaying()
            }
        } returns flow { Response.Success(movieNowPlayingEntity) }

        // Act
        runBlocking {
            getListMovieNowPlayingUseCase.execute()
        }
        verify(exactly = 1) {
            runBlocking {
                moviesRepository.getMoviesNowPlaying()
            }
        }
    }
}