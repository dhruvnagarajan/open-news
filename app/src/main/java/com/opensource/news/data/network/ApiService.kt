package com.opensource.news.data.network

import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Dhruvaraj Nagarajan
 */
interface ApiService {

    @GET("top-headlinesUseCase")
    fun getTopHeadlines(
        @Query("sources") sources: String? = null,
        @Query("q") q: String? = null,
        @Query("language") language: String? = null,
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String
    ): Observable<Response<NewsResponse>>
}