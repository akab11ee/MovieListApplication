package com.restaurant.movielistapplication.di

import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import com.restaurant.movielistapplication.domain.usecase.GetListMovieNowPlayingUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMovieTopRatedUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMovieUpcomingUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMoviesPopularUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
    @Provides
    fun providesGetListMoviesPopularUseCase(moviesRepository: MoviesRepository): GetListMoviesPopularUseCase {
        return GetListMoviesPopularUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    fun providesGetListMoviesUpcomingUseCase(moviesRepository: MoviesRepository): GetListMovieUpcomingUseCase {
        return GetListMovieUpcomingUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    fun providesGetListMoviesTopRatedUseCase(moviesRepository: MoviesRepository): GetListMovieTopRatedUseCase {
        return GetListMovieTopRatedUseCase(moviesRepository = moviesRepository)
    }

    @Provides
    fun providesGetListMoviesNowPlaying(moviesRepository: MoviesRepository): GetListMovieNowPlayingUseCase {
        return GetListMovieNowPlayingUseCase(moviesRepository = moviesRepository)
    }
}