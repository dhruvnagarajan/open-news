package com.opensource.news.domain.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import io.reactivex.Observable

/**
 * @author dhruvaraj
 */
interface NewsRepository {

    fun getConfig(): Observable<GetTopHeadlinesUseCase.Params>

    fun getTopHeadlines(params: GetTopHeadlinesUseCase.Params): Observable<BaseResponse<NewsResponse>>
}