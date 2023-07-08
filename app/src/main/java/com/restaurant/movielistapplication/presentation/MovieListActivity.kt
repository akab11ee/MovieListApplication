package com.restaurant.movielistapplication.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.restaurant.movielistapplication.base.BaseActivity
import com.restaurant.movielistapplication.databinding.ActivityMovieBinding
import com.restaurant.movielistapplication.domain.models.MovieDetails
import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.presentation.adapter.MovieParentAdapter
import com.restaurant.movielistapplication.presentation.dialog.MovieDetailBottomSheetDialog
import com.restaurant.movielistapplication.presentation.interfaces.OnParentItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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
        viewModel.getListMoviesPopular()
        viewModel.getListNowPlayingMovies()
        initRecyclerView()
        observeMovieListResponse()
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
        viewModel.setMovieState(movie)
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