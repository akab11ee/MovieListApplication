package com.restaurant.movielistapplication.domain.usecase

import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import com.restaurant.movielistapplication.getMovieSectionsEntityResponse
import com.restaurant.movielistapplication.utils.AppConstant
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

class GetListMovieSectionsUseCaseTest {
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getListMovieSectionsUseCase: GetListMovieSectionsUseCase

    @Before
    fun setUp() {
        moviesRepository = mockk(relaxUnitFun = true)
        getListMovieSectionsUseCase = GetListMovieSectionsUseCase(moviesRepository)
    }

    @Test
    fun `execute method should call repository`() {
        // Arrange
        val movieNowPlayingEntity = getMovieSectionsEntityResponse()
        every {
            runBlocking {
                moviesRepository.getMovieSections(AppConstant.NOW_PLAYING)
            }
        } returns flow { Response.Success(movieNowPlayingEntity) }

        // Act
        runBlocking {
            getListMovieSectionsUseCase.execute(AppConstant.NOW_PLAYING)
        }
        verify(exactly = 1) {
            runBlocking {
                moviesRepository.getMovieSections(AppConstant.NOW_PLAYING)
            }
        }
    }
}