package com.dhruvnagarajan.opennews.view.progressbottomsheet

/**
 * @author Dhruvaraj Nagarajan
 */
interface ViewStatePrompt {

    fun showLoading(message: String? = "Loading...")
    fun showError(message: String? = "Error occurred!")
    fun dismiss()
}