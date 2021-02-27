package com.opensource.news.view.progressbottomsheet

/**
 * @author Dhruvaraj Nagarajan
 */
interface ViewStatePrompt {

    fun showLoading(message: String? = "Loading...")
    fun showError(message: String? = "Error occurred!")
    fun dismiss()
}