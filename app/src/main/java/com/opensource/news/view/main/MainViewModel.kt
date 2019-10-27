package com.opensource.news.view.main

import com.dhruvnagarajan.androidplatform.view.BaseViewModel
import com.opensource.news.domain.entity.NewsProfile
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

    fun fetchNews(profile: NewsProfile) =
        headlinesUseCase.getTopHeadlines(profile)
            .map {
                val list = ArrayList<NewsAdapter.Item>()
                for (article in it.newsArticles!!) {
                    list.add(NewsAdapter.Item(profile, article))
                }
                list
            }

    fun fetchNewsProfiles() =
        profileUseCase.getNewsProfiles()

    fun createNewsProfile(newsProfile: NewsProfile) =
        profileUseCase.createNewsProfile(newsProfile)
}