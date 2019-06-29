package com.opensource.news.view.progressbottomsheet

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class ViewStatePromptImpl @Inject constructor() : ViewStatePrompt {

    @Inject
    lateinit var activity: AppCompatActivity

    private val tag = ProgressBottomSheet::class.java.name
    private val progressBottomSheet = ProgressBottomSheet()

    override fun showLoading(message: String?) {
        progressBottomSheet.viewStateType = ProgressBottomSheet.ViewType.LOADING
        progressBottomSheet.message = message
        progressBottomSheet.show(activity.supportFragmentManager, tag)
    }

    override fun showError(message: String?) {
        progressBottomSheet.viewStateType = ProgressBottomSheet.ViewType.ERROR
        progressBottomSheet.message = message
        progressBottomSheet.show(activity.supportFragmentManager, tag)
    }

    override fun dismiss() {
        if (progressBottomSheet.isVisible)
            progressBottomSheet.dismiss()
    }
}
