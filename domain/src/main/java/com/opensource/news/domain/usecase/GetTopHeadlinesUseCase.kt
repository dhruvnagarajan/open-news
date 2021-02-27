package com.opensource.news.domain.usecase

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable

/**
 * Returns top headlines for given query.
 *
 * @author Dhruvaraj Nagarajan
 */
class GetTopHeadlinesUseCase(private val newsRepository: NewsRepository) :
    UseCase<Unit, BaseResponse<NewsResponse>> {

    override fun execute(unit: Unit): Observable<BaseResponse<NewsResponse>> {
        return newsRepository.getConfig().flatMap {
            newsRepository.getTopHeadlines(it)
        }
    }

    data class Params(
        val sources: String? = null,
        val q: String? = null,
        val language: String? = null,
        val country: String? = null,
        val category: String? = null
    )
}