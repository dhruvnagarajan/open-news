package com.dhruvnagarajan.opennews.view.progressbottomsheet

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
