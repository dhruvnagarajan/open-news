package com.opensource.news.view.main

import com.dhruvnagarajan.androidcore.view.BaseViewModel
import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.usecase.HeadlinesUseCase
import com.opensource.news.domain.usecase.ProfileUseCase
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainViewModel @Inject constructor(
    val headlinesUseCase: HeadlinesUseCase,
    val profileUseCase: ProfileUseCase
) : BaseViewModel() {

    fun fetchNews(request: NewsRequest) =
        headlinesUseCase.getTopHeadlines(request)
            .map {
                val list = ArrayList<NewsAdapter.Item>()
                for (article in it.articles!!) {
                    list.add(NewsAdapter.Item(request, article))
                }
                list
            }

    fun fetchNewsProfiles() =
        profileUseCase.getNewsProfiles()

    fun createNewsProfile(newsProfile: NewsRequest) =
        profileUseCase.createNewsProfile(newsProfile)
}