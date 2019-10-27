package com.opensource.news.view.progressbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.dhruvnagarajan.androidplatform.view.BaseBottomSheetFragment
import com.opensource.news.R
import kotlinx.android.synthetic.main.bottom_sheet_progress.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class ProgressBottomSheet : BaseBottomSheetFragment() {

    var message: String? = null

    private lateinit var iv_close: ImageView
    private lateinit var progressbar: ProgressBar
    private lateinit var tv_message: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_close = view.iv_close
        progressbar = view.progressbar
        tv_message = view.tv_message

        isCancelable = false

        // todo : fix this

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
}