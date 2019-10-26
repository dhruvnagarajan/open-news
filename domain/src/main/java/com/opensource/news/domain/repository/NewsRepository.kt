package com.opensource.news.domain.repository

import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface NewsRepository {

    fun getTopHeadlines(request: NewsRequest): Observable<NewsResponse>
}