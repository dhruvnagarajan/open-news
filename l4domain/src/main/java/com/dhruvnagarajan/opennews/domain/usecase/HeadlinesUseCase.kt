package com.dhruvnagarajan.opennews.domain.usecase

import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.entity.NewsResponse
import com.dhruvnagarajan.opennews.domain.repository.NewsRepository
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