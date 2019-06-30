package com.opensource.news.view.progressbottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opensource.news.R
import com.opensource.news.view.base.BaseBottomSheetDialogFragment
import com.opensource.news.view.base.BaseViewModel
import kotlinx.android.synthetic.main.bottom_sheet_progress.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class ProgressBottomSheet : BaseBottomSheetDialogFragment() {

    lateinit var viewModel: BaseViewModel

    var message: String? = null

    private lateinit var iv_close: ImageView
    private lateinit var progressbar: ProgressBar
    private lateinit var tv_message: TextView

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.bottom_sheet_progress, container, false)
    }

    override fun onCreateView(view: View) {
        viewModel = ViewModelProviders.of(activity!!)[BaseViewModel::class.java]

        iv_close = view.iv_close
        progressbar = view.progressbar
        tv_message = view.tv_message

        isCancelable = false


        viewModel.viewStateLiveData.observe(this, Observer {
            message = it.message
            when {
                it.viewStateType == BaseViewModel.ViewStateType.LOADING -> showLoading()
                it.viewStateType == BaseViewModel.ViewStateType.ERROR -> showError()
                it.viewStateType == BaseViewModel.ViewStateType.NONE -> dismiss()
            }
        })

        iv_close.setOnClickListener { this@ProgressBottomSheet.dismiss() }
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