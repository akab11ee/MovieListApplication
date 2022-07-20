package com.restaurant.movielistapplication.di.qualifier

import javax.inject.Qualifier

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
