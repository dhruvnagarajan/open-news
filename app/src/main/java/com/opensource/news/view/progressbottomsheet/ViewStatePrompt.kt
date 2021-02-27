package com.opensource.news.view.progressbottomsheet

/**
 * @author dhruvaraj
 */
interface ViewStatePrompt {

    fun showLoading(message: String? = "Loading...")
    fun showError(message: String? = "Error occurred!")
    fun dismiss()
}