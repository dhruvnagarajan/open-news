package com.opensource.news.data.repository

import com.opensource.news.data.network.news.NewsNetworkSourceImpl
import com.opensource.news.data.persistence.news.NewsDataSourceImpl
import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.entity.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsRepositoryImpl @Inject constructor(
    newsNetworkSourceImpl: NewsNetworkSourceImpl,
    newsLocalSourceImpl: NewsDataSourceImpl
) : OfflineFirstRepository<NewsRequest, NewsResponse>(
    newsLocalSourceImpl, newsNetworkSourceImpl
), NewsRepository {

    override fun getTopHeadlines(request: NewsRequest): Observable<NewsResponse> =
        getFromAnySource(request)
}