package com.restaurant.movielistapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.restaurant.movielistapplication.utils.toastL
import com.restaurant.movielistapplication.utils.view.CustomProgressDialog

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> :
    AppCompatActivity() {
    abstract val viewModel: VM
    abstract fun getViewBinding(): VB
    private var _binding: ViewBinding? = null
    protected val binding: VB
        get() = _binding as VB
    private val progressDialog = CustomProgressDialog()

    //Initialize UI and listeners
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        setUpLoadingDialog()
        setupErrorMessage()
    }

    //Show error toast in case of any coroutine exception
    private fun setupErrorMessage() {
        lifecycleScope.launchWhenCreated {
            viewModel.showErrorToast.collect {
                showErrorToastToUser(description = it)
            }
        }
    }

    private fun showErrorToastToUser(description: String) {
        if (description.isNotEmpty()) {
            toastL(description)
        }
    }


    //show loading dialog when user calls this method
    private fun setUpLoadingDialog() {
        lifecycleScope.launchWhenCreated {
            viewModel.showLoadingDialog.collect { loading ->
                if (loading) {
                    progressDialog.show(this@BaseActivity)
                } else {
                    progressDialog.dialog?.dismiss()
                }
            }
        }
    }
}