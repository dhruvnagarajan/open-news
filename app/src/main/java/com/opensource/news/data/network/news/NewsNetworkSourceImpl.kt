package com.opensource.news.data.network.news

import com.opensource.news.data.AppConstants
import com.opensource.news.data.network.ApiService
import com.opensource.news.data.network.NetworkSource
import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsNetworkSourceImpl @Inject constructor(
    val apiService: ApiService
) : NetworkSource<NewsRequest, NewsResponse> {

    override fun get(request: NewsRequest): Observable<NewsResponse> =
        apiService.getTopHeadlines(
            request.sources,
            request.q,
            request.language,
            request.country,
            request.category,
            AppConstants.API_KEY
        ).flatMap { response ->
            Observable.create<NewsResponse> {
                if (response.isSuccessful && response.body() != null) {
                    it.onNext(response.body()!!)
                    it.onComplete()
                } else {
                    it.onError(Throwable(response.message()))
                }
            }
        }
}