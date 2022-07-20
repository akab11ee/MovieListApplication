package com.restaurant.movielistapplication.di

import com.restaurant.movielistapplication.BuildConfig
import com.restaurant.movielistapplication.data.repository.MoviesRepositoryImpl
import com.restaurant.movielistapplication.data.storage.MoviesStorage
import com.restaurant.movielistapplication.data.storage.themoviedb.MovieApiService
import com.restaurant.movielistapplication.data.storage.themoviedb.MovieApiServiceImpl
import com.restaurant.movielistapplication.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): MovieApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun providesMovieStorage(movieApiService: MovieApiService): MoviesStorage {
        return MovieApiServiceImpl(movieApiService = movieApiService)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(moviesStorage: MoviesStorage): MoviesRepository {
        return MoviesRepositoryImpl(moviesStorage = moviesStorage)
    }
}