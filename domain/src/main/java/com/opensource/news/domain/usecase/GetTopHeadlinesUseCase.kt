package com.opensource.news.domain.usecase

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class GetTopHeadlinesUseCase(private val newsRepository: NewsRepository) :
    UseCase<GetTopHeadlinesUseCase.Params, BaseResponse<NewsResponse>> {
    override fun execute(params: Params): Observable<BaseResponse<NewsResponse>> {
        return newsRepository.getTopHeadlines(params)
    }

    data class Params(
        val sources: String? = null,
        val q: String? = null,
        val language: String? = null,
        val country: String? = null,
        val category: String? = null
    )
}