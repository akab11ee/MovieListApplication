package com.restaurant.movielistapplication.di

import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import com.restaurant.movielistapplication.domain.usecase.GetListMovieSectionsUseCase
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
    fun providesGetListMovieSectionsUseCase(moviesRepository: MoviesRepository): GetListMovieSectionsUseCase {
        return GetListMovieSectionsUseCase(moviesRepository = moviesRepository)
    }
}