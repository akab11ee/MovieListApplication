package com.restaurant.movielistapplication.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

open class BaseViewModel : ViewModel() {

    val showLoadingDialog = MutableStateFlow(false)
    val showErrorToast = MutableStateFlow("")

    //capture coroutine exception and show it in dialog to user
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        hideLoading()
        exception.localizedMessage?.let { showErrorToast(it) }
    }

    fun showLoading() {
        if (!showLoadingDialog.value) {
            showLoadingDialog.value = true
        }
    }

    fun hideLoading() {
        if (showLoadingDialog.value) {
            showLoadingDialog.value = false
        }
    }

    fun showErrorToast(error: String) {
        hideLoading()
        showErrorToast.value = error
    }
}