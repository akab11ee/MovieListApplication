package com.restaurant.movielistapplication.presentation

import androidx.lifecycle.viewModelScope
import com.restaurant.movielistapplication.base.BaseViewModel
import com.restaurant.movielistapplication.di.qualifier.IoDispatcher
import com.restaurant.movielistapplication.domain.models.MovieDetails
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.domain.usecase.GetListMovieNowPlayingUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMovieTopRatedUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMovieUpcomingUseCase
import com.restaurant.movielistapplication.domain.usecase.GetListMoviesPopularUseCase
import com.restaurant.movielistapplication.presentation.state.MovieState
import com.restaurant.movielistapplication.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getListMoviesPopularUseCase: GetListMoviesPopularUseCase,
    private val getListMovieNowPlayingUseCase: GetListMovieNowPlayingUseCase,
    private val getListMovieUpcomingUseCase: GetListMovieUpcomingUseCase,
    private val getListMovieTopRatedUseCase: GetListMovieTopRatedUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var nowPlayingMovieDetails = listOf<MovieDetails>()
    private var popularMovieDetail = listOf<MovieDetails>()
    private var upcomingMovieDetails = listOf<MovieDetails>()
    private var topRatedMovieDetails = listOf<MovieDetails>()
    private var movieState = MovieState()

    private val _listMovies = MutableStateFlow(
        getDefaultMovieList(
            nowPlayingMovieDetails,
            popularMovieDetail,
            upcomingMovieDetails,
            topRatedMovieDetails
        )
    )
    val listMovies = _listMovies.asStateFlow()

    fun getDefaultMovieList(
        nowPlayingMovieDetails: List<MovieDetails>,
        popularMovieDetail: List<MovieDetails>,
        upcomingMovieDetails: List<MovieDetails>,
        topRatedMovieDetails: List<MovieDetails>
    ): List<Movie> {
        this.nowPlayingMovieDetails = nowPlayingMovieDetails
        this.popularMovieDetail = popularMovieDetail
        this.upcomingMovieDetails = upcomingMovieDetails
        this.topRatedMovieDetails = topRatedMovieDetails
        val nowPlayingMovie = Movie(
            isExpanded = movieState.isNowPlayingExpanded,
            sectionName = AppConstant.NOW_PLAYING,
            this.nowPlayingMovieDetails
        )
        val popularMovie = Movie(
            isExpanded = movieState.isPopularMovieExpanded,
            sectionName = AppConstant.MOST_POPULAR,
            this.popularMovieDetail
        )
        val upcomingMovie = Movie(
            isExpanded = movieState.isUpcomingMovieExpanded,
            sectionName = AppConstant.UPCOMING,
            this.upcomingMovieDetails
        )
        val topRatedMovie = Movie(
            isExpanded = movieState.isTopRatedMovieExpanded,
            sectionName = AppConstant.TOP_RATED,
            this.topRatedMovieDetails
        )
        return listOf(nowPlayingMovie, popularMovie, upcomingMovie, topRatedMovie)
    }

    fun getListNowPlayingMovies() {

        viewModelScope.launch(coroutineExceptionHandler) {
            getListMovieNowPlayingUseCase.execute()
                .flowOn(ioDispatcher)
                .collect { result ->
                    when (result) {
                        is Response.Loading -> showLoading()
                        is Response.Fail -> {
                            result.exception.localizedMessage?.let { showErrorToast(it) }
                        }
                        is Response.Success -> {
                            hideLoading()
                            _listMovies.emit(
                                getDefaultMovieList(
                                    nowPlayingMovieDetails = result.data.results,
                                    popularMovieDetail = popularMovieDetail,
                                    upcomingMovieDetails = upcomingMovieDetails,
                                    topRatedMovieDetails = topRatedMovieDetails
                                )
                            )
                            if (movieState.isNowPlayingExpanded) {
                                delay(AppConstant.DELAY_IN_MILLIS)
                                getListNowPlayingMovies()
                            }

                        }
                    }
                }
        }
    }


    fun getListMoviesPopular() {
        if (popularMovieDetail.isEmpty()) {
            viewModelScope.launch(coroutineExceptionHandler) {
                getListMoviesPopularUseCase.execute()
                    .flowOn(ioDispatcher)
                    .collect { result ->
                        when (result) {
                            is Response.Loading -> showLoading()
                            is Response.Fail -> {
                                result.exception.localizedMessage?.let { showErrorToast(it) }
                            }
                            is Response.Success -> {
                                hideLoading()
                                _listMovies.emit(
                                    getDefaultMovieList(
                                        popularMovieDetail = result.data.moviesPopularDetails,
                                        nowPlayingMovieDetails = nowPlayingMovieDetails,
                                        upcomingMovieDetails = upcomingMovieDetails,
                                        topRatedMovieDetails = topRatedMovieDetails
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }


    private fun getListUpcomingMovies() {
        if (upcomingMovieDetails.isEmpty()) {
            viewModelScope.launch(coroutineExceptionHandler) {
                getListMovieUpcomingUseCase.execute()
                    .flowOn(ioDispatcher)
                    .collect { result ->
                        when (result) {
                            is Response.Loading -> showLoading()
                            is Response.Fail -> {
                                result.exception.localizedMessage?.let { showErrorToast(it) }
                            }
                            is Response.Success -> {
                                hideLoading()
                                _listMovies.emit(
                                    getDefaultMovieList(
                                        upcomingMovieDetails = result.data.results,
                                        popularMovieDetail = popularMovieDetail,
                                        topRatedMovieDetails = topRatedMovieDetails,
                                        nowPlayingMovieDetails = nowPlayingMovieDetails
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }


    private fun getListTopRatedMovies() {
        if (topRatedMovieDetails.isEmpty()) {
            viewModelScope.launch(coroutineExceptionHandler) {
                getListMovieTopRatedUseCase.execute()
                    .flowOn(ioDispatcher)
                    .collect { result ->
                        when (result) {
                            is Response.Loading -> showLoading()
                            is Response.Fail -> {
                                result.exception.localizedMessage?.let { showErrorToast(it) }
                            }
                            is Response.Success -> {
                                hideLoading()
                                _listMovies.emit(
                                    getDefaultMovieList(
                                        topRatedMovieDetails = result.data.results,
                                        popularMovieDetail = popularMovieDetail,
                                        upcomingMovieDetails = upcomingMovieDetails,
                                        nowPlayingMovieDetails = nowPlayingMovieDetails
                                    )
                                )
                            }
                        }

                    }
            }
        }
    }

    fun setMovieState(movie: Movie) {
        when (movie.sectionName) {
            AppConstant.NOW_PLAYING -> {
                movieState.isNowPlayingExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getListNowPlayingMovies()
                }
            }
            AppConstant.UPCOMING -> {
                movieState.isUpcomingMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getListUpcomingMovies()
                }
            }
            AppConstant.TOP_RATED -> {
                movieState.isTopRatedMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getListTopRatedMovies()
                }
            }
            AppConstant.MOST_POPULAR -> {
                movieState.isPopularMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getListMoviesPopular()
                }
            }
        }

    }
}