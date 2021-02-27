package com.opensource.news.view.progressbottomsheet

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class ViewStatePromptImpl @Inject constructor() : ViewStatePrompt {

    @Inject
    lateinit var activity: AppCompatActivity

    private val tag = ProgressBottomSheet::class.java.name
    private val progressBottomSheet = ProgressBottomSheet().apply { onCancelled = { } }

    override fun showLoading(message: String?) {
        if (activity.supportFragmentManager.findFragmentByTag(tag) == null)
            progressBottomSheet.show(activity.supportFragmentManager, tag)
    }

    override fun showError(message: String?) {
        if (activity.supportFragmentManager.findFragmentByTag(tag) == null)
            progressBottomSheet.show(activity.supportFragmentManager, tag)
    }

    override fun dismiss() {
        if (progressBottomSheet.isVisible)
            progressBottomSheet.dismiss()
    }
}
