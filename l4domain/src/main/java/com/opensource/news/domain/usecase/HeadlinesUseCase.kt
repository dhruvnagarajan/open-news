package com.opensource.news.domain.usecase

import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.domain.entity.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class HeadlinesUseCase constructor(
    private val newsRepository: NewsRepository
) {

    fun getTopHeadlines(profile: NewsProfile): Observable<NewsResponse> =
        newsRepository.getCacheLedgerEntry(profile.toString())
            .flatMap {
                val currentTime = System.currentTimeMillis()
                if (currentTime - it.lastUpdateTime > 30 * 1000 * 60) {
                    newsRepository.getTopHeadlines(profile)
                } else {
                    throw Throwable()
                }
            }
}