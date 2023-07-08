package com.restaurant.movielistapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.restaurant.movielistapplication.R
import com.restaurant.movielistapplication.databinding.ItemMovieChildBinding
import com.restaurant.movielistapplication.domain.models.moviesection.MovieDetails
import com.restaurant.movielistapplication.presentation.interfaces.OnChildItemClickListener

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MovieDetailChildAdapter :
    ListAdapter<MovieDetails, MovieDetailChildAdapter.MovieDetailViewHolder>(MovieDetailComparator) {

    private var callback: OnChildItemClickListener? = null

    object MovieDetailComparator : DiffUtil.ItemCallback<MovieDetails>() {
        override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean =
            oldItem == newItem
    }

    fun setCallback(callback: OnChildItemClickListener) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieDetailViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_child,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieDetailViewHolder, position: Int) {
        holder.binding.item = getItem(position)
        holder.binding.movieItem.setOnClickListener {
            callback?.onMovieClick(getItem(position))
        }
    }

    class MovieDetailViewHolder(val binding: ItemMovieChildBinding) :
        RecyclerView.ViewHolder(binding.root)

}