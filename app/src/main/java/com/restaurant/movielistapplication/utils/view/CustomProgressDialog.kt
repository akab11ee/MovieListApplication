package com.restaurant.movielistapplication.utils.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.restaurant.movielistapplication.R

/**
 * @Author: Akash Abhishek
 * @Date: 20 July 2022
 */

class CustomProgressDialog {

    var dialog: CustomDialog? = null

    fun show(context: Context): Dialog? {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog_view, null)

        dialog = CustomDialog(context)
        dialog?.setContentView(view)
        dialog?.show()
        return dialog
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(R.color.transparent)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}