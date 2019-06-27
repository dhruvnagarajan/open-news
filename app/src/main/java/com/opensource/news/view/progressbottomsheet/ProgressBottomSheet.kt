package com.opensource.news.view.progressbottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.opensource.news.R
import com.opensource.news.view.base.BaseBottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_progress.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class ProgressBottomSheet : BaseBottomSheetDialogFragment() {

    var viewStateType = ViewType.NONE
    var message: String? = null

    private lateinit var iv_close: ImageView
    private lateinit var progressbar: ProgressBar
    private lateinit var tv_message: TextView

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.bottom_sheet_progress, container, false)
    }

    override fun onCreateView(view: View) {
        iv_close = view.iv_close
        progressbar = view.progressbar
        tv_message = view.tv_message

        iv_close.setOnClickListener { this@ProgressBottomSheet.dismiss() }

        if (viewStateType == ViewType.LOADING) showLoading()
        else if (viewStateType == ViewType.ERROR) showError()
    }

    private fun showLoading() {
        iv_close.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
        tv_message.text = message
    }

    private fun showError() {
        iv_close.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        tv_message.text = message
    }

    enum class ViewType {
        NONE, LOADING, ERROR
    }
}