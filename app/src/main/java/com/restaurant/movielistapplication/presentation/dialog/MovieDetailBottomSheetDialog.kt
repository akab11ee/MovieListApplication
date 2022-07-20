package com.restaurant.movielistapplication.presentation.dialog

import android.os.Bundle
import android.view.View
import com.restaurant.movielistapplication.base.BaseBottomSheetDialog
import com.restaurant.movielistapplication.databinding.LayoutDialogBinding
import com.restaurant.movielistapplication.domain.models.MovieDetails
import com.restaurant.movielistapplication.utils.AppConstant
import com.restaurant.movielistapplication.utils.toastL
import kotlinx.android.synthetic.main.layout_dialog.*

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class MovieDetailBottomSheetDialog : BaseBottomSheetDialog<LayoutDialogBinding>() {

    companion object {
        fun newInstance(movieDetails: MovieDetails): MovieDetailBottomSheetDialog {
            val fragment = MovieDetailBottomSheetDialog()
            val args = Bundle()
            args.putParcelable(AppConstant.MOVIE_DETAIL_KEY, movieDetails)
            fragment.arguments = args
            return fragment
        }
    }

    var movieDetails: MovieDetails? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.containsKey(AppConstant.MOVIE_DETAIL_KEY) == true) {
            movieDetails = arguments?.getParcelable(AppConstant.MOVIE_DETAIL_KEY) as MovieDetails?
        }
        binding.apply {
            if (arguments?.containsKey(AppConstant.MOVIE_DETAIL_KEY) == true) {
                item = movieDetails
            }

        }

        img_video.setOnClickListener {
            movieDetails?.title?.let { it1 -> requireActivity().toastL(it1) }
        }
    }

    override fun getViewBinding(): LayoutDialogBinding = LayoutDialogBinding.inflate(layoutInflater)

}