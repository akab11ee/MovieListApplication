package com.restaurant.movielistapplication.presentation

import androidx.lifecycle.viewModelScope
import com.restaurant.movielistapplication.base.BaseViewModel
import com.restaurant.movielistapplication.di.qualifier.IoDispatcher
import com.restaurant.movielistapplication.domain.models.Response
import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.domain.models.moviesection.ListMovieSections
import com.restaurant.movielistapplication.domain.models.moviesection.MovieDetails
import com.restaurant.movielistapplication.domain.usecase.GetListMovieSectionsUseCase
import com.restaurant.movielistapplication.presentation.state.MovieState
import com.restaurant.movielistapplication.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getListMovieSectionsUseCase: GetListMovieSectionsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private var listNowPlayingMovie = listOf<MovieDetails>()
    private var listPopularMovie = listOf<MovieDetails>()
    private var listUpComingMovie = listOf<MovieDetails>()
    private var listTopRatedMovie = listOf<MovieDetails>()
    private var movieState = MovieState()

    private val _listMovies = MutableStateFlow(
        getDefaultMovieList(
            listNowPlayingMovie,
            listPopularMovie,
            listUpComingMovie,
            listTopRatedMovie
        )
    )
    val listMovies = _listMovies.asStateFlow()

    private fun getDefaultMovieList(
        nowPlayingMovieDetails: List<MovieDetails>,
        popularMovieDetail: List<MovieDetails>,
        upcomingMovieDetails: List<MovieDetails>,
        topRatedMovieDetails: List<MovieDetails>
    ): List<Movie> {
        this.listNowPlayingMovie = nowPlayingMovieDetails
        this.listPopularMovie = popularMovieDetail
        this.listUpComingMovie = upcomingMovieDetails
        this.listTopRatedMovie = topRatedMovieDetails
        val nowPlayingMovie = Movie(
            isExpanded = movieState.isNowPlayingExpanded,
            sectionName = AppConstant.NOW_PLAYING,
            this.listNowPlayingMovie
        )
        val popularMovie = Movie(
            isExpanded = movieState.isPopularMovieExpanded,
            sectionName = AppConstant.MOST_POPULAR,
            this.listPopularMovie
        )
        val upcomingMovie = Movie(
            isExpanded = movieState.isUpcomingMovieExpanded,
            sectionName = AppConstant.UPCOMING,
            this.listUpComingMovie
        )
        val topRatedMovie = Movie(
            isExpanded = movieState.isTopRatedMovieExpanded,
            sectionName = AppConstant.TOP_RATED,
            this.listTopRatedMovie
        )
        return listOf(nowPlayingMovie, popularMovie, upcomingMovie, topRatedMovie)
    }

    fun getMovieSectionDetails(vararg sectionName: String) {
        sectionName.forEach { section ->
            viewModelScope.launch(coroutineExceptionHandler) {
                getListMovieSectionsUseCase.execute(getEndPoint(section)).flowOn(ioDispatcher)
                    .collect { result ->
                        when (result) {
                            is Response.Loading -> showLoading()
                            is Response.Fail -> {
                                result.exception.localizedMessage?.let { showErrorToast(it) }
                            }

                            is Response.Success -> {
                                hideLoading()
                                observeDataBasedOnSection(section, result.data)
                            }
                        }
                    }
            }
        }
    }

    private fun getEndPoint(section: String): String {
        return when (section) {
            AppConstant.NOW_PLAYING -> AppConstant.NOW_PLAYING_ENDPOINT
            AppConstant.MOST_POPULAR -> AppConstant.POPULAR_MOVIE_ENDPOINT
            AppConstant.UPCOMING -> AppConstant.UPCOMING_MOVIE_ENDPOINT
            AppConstant.TOP_RATED -> AppConstant.TOP_RATED_ENDPOINT
            else -> ""
        }
    }

    private suspend fun observeDataBasedOnSection(sectionName: String, data: ListMovieSections) {
        when (sectionName) {
            AppConstant.NOW_PLAYING -> {
                _listMovies.emit(
                    getDefaultMovieList(
                        data.results,
                        listPopularMovie,
                        listUpComingMovie,
                        listTopRatedMovie
                    )
                )
            }

            AppConstant.MOST_POPULAR -> {
                _listMovies.emit(
                    getDefaultMovieList(
                        listNowPlayingMovie,
                        data.results,
                        listUpComingMovie,
                        listTopRatedMovie
                    )
                )
            }

            AppConstant.UPCOMING -> {
                _listMovies.emit(
                    getDefaultMovieList(
                        listNowPlayingMovie,
                        listPopularMovie,
                        data.results,
                        listTopRatedMovie
                    )
                )
            }

            AppConstant.TOP_RATED -> {
                _listMovies.emit(
                    getDefaultMovieList(
                        listNowPlayingMovie,
                        listPopularMovie,
                        listUpComingMovie,
                        data.results
                    )
                )
            }
        }

    }

    fun setMovieState(movie: Movie) {
        when (movie.sectionName) {
            AppConstant.NOW_PLAYING -> {
                movieState.isNowPlayingExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getMovieSectionDetails(AppConstant.NOW_PLAYING)
                }
            }

            AppConstant.UPCOMING -> {
                movieState.isUpcomingMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getMovieSectionDetails(AppConstant.UPCOMING)
                }
            }

            AppConstant.TOP_RATED -> {
                movieState.isTopRatedMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getMovieSectionDetails(AppConstant.TOP_RATED)
                }
            }

            AppConstant.MOST_POPULAR -> {
                movieState.isPopularMovieExpanded = movie.isExpanded
                if (movie.isExpanded) {
                    getMovieSectionDetails(AppConstant.MOST_POPULAR)
                }
            }
        }

    }
}