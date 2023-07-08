package com.restaurant.movielistapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.movielistapplication.R
import com.restaurant.movielistapplication.databinding.ItemMovieParentBinding
import com.restaurant.movielistapplication.domain.models.MovieDetails
import com.restaurant.movielistapplication.domain.models.movie.Movie
import com.restaurant.movielistapplication.presentation.interfaces.OnChildItemClickListener
import com.restaurant.movielistapplication.presentation.interfaces.OnParentItemClickListener

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MovieParentAdapter :
    ListAdapter<Movie, MovieParentAdapter.MovieViewHolder>(MovieComparator),
    OnChildItemClickListener {


    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.sectionName == newItem.sectionName

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    lateinit var onItemClickListener: OnParentItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_parent,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.item = movie
        val childAdapter = MovieDetailChildAdapter()
        holder.binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
            adapter = childAdapter
            childAdapter.setCallback(this@MovieParentAdapter)
            childAdapter.submitList(movie.movieDetailList)
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }

        holder.binding.movieItem.setOnClickListener {
            onItemClick(position)
        }
    }

    private fun onItemClick(position: Int) {
        getItem(position).isExpanded = !getItem(position).isExpanded
        notifyItemChanged(position)
        onItemClickListener.onItemClick(getItem(position))
    }

    class MovieViewHolder(val binding: ItemMovieParentBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onMovieClick(movieDetails: MovieDetails) {
        onItemClickListener.onMovieClick(movieDetails)
    }
}