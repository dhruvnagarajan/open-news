package com.opensource.news.view.main

import com.dhruvnagarajan.androidcore.view.BaseViewModel
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase
) : BaseViewModel() {

    fun fetchNews(params: GetTopHeadlinesUseCase.Params) =
        getTopHeadlines.execute(params)
}