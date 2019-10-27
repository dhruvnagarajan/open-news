package com.opensource.news.data.network.news

import com.opensource.news.data.AppConstants
import com.opensource.news.data.network.ApiService
import com.opensource.news.data.network.RemoteDataSource
import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsRemoteDataSourceImpl @Inject constructor(
    val apiService: ApiService
) : RemoteDataSource<NewsProfile, NewsResponse> {

    override fun get(request: NewsProfile): Observable<NewsResponse> =
        apiService.getTopHeadlines(
            request.sources,
            request.q,
            request.language,
            request.country,
            request.category,
            AppConstants.API_KEY
        ).map {
            if (!it.isSuccessful || it.body() == null) {
                throw Throwable(it.errorBody().toString())
            }
            it.body()
        }
}