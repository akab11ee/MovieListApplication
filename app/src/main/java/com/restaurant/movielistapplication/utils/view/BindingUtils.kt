package com.restaurant.movielistapplication.utils.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.restaurant.movielistapplication.R

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

@BindingAdapter("load_image")
fun loadImage(view: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        // dummy img
        Glide.with(view)
            .load("https://github.githubassets.com/images/modules/open_graph/github-mark.png")
            .into(view)
    } else {
        Glide.with(view)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("load_arrow_image")
fun loadArrowImage(view: ImageView, isExpanded: Boolean) {
    if (isExpanded) {
        view.setImageResource(R.drawable.ic_arrow_open)
    } else {
        view.setImageResource(R.drawable.ic_arrow_close)
    }
}