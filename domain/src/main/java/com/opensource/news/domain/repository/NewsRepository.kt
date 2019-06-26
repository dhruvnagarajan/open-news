package com.opensource.news.domain.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlines
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface NewsRepository {

    fun getTopHeadlines(params: GetTopHeadlines.Params): Observable<BaseResponse<NewsResponse>>
}