package com.restaurant.movielistapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.restaurant.movielistapplication.R
import com.restaurant.movielistapplication.base.BaseActivity
import com.restaurant.movielistapplication.databinding.ActivityMovieBinding
import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.domain.models.moviesection.MovieDetails
import com.restaurant.movielistapplication.presentation.adapter.MovieParentAdapter
import com.restaurant.movielistapplication.presentation.dialog.MovieDetailBottomSheetDialog
import com.restaurant.movielistapplication.presentation.interfaces.OnParentItemClickListener
import com.restaurant.movielistapplication.utils.AppConstant
import com.restaurant.movielistapplication.utils.isConnected
import com.restaurant.movielistapplication.utils.toastL
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@AndroidEntryPoint
class MovieListActivity : BaseActivity<ActivityMovieBinding, MovieListViewModel>(),
    OnParentItemClickListener {

    private val TAG = MovieListActivity::class.simpleName
    private lateinit var movieParentAdapter: MovieParentAdapter

    override val viewModel: MovieListViewModel by viewModels()

    override fun getViewBinding(): ActivityMovieBinding =
        ActivityMovieBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInitialData()
        initRecyclerView()
        observeMovieListResponse()
    }

    // Populating data for now playing and most popular on app start
    private fun getInitialData() {
        when {
            isConnected -> viewModel.getMovieSectionDetails(
                AppConstant.NOW_PLAYING,
                AppConstant.MOST_POPULAR
            )

            else -> toastL(getString(R.string.no_internet))
        }
    }


    private fun initRecyclerView() {
        movieParentAdapter = MovieParentAdapter()
        movieParentAdapter.onItemClickListener = this
        binding.expandableRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieParentAdapter
        }
    }

    private fun observeMovieListResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.listMovies.collect { listMovie ->
                movieParentAdapter.submitList(listMovie)
            }
        }

    }

    override fun onItemClick(movie: Movie) {
        when {
            isConnected -> viewModel.setMovieState(movie)
            else -> toastL(getString(R.string.no_internet))
        }
    }

    override fun onMovieClick(movie: MovieDetails) {
        val movieDetailBottomSheetDialog: MovieDetailBottomSheetDialog =
            MovieDetailBottomSheetDialog.newInstance(movie)
        movieDetailBottomSheetDialog.show(
            supportFragmentManager,
            TAG
        )
    }
}