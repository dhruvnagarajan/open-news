package com.opensource.news.domain.usecase

import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.entity.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable

/**
 * Returns top headlines for given query.
 *
 * @author Dhruvaraj Nagarajan
 */
class HeadlinesUseCase constructor(
    val newsRepository: NewsRepository
) {

    fun getTopHeadlines(request: NewsRequest): Observable<NewsResponse> =
        newsRepository.getTopHeadlines(request)
}