package data.repository

import com.google.gson.Gson
import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.util.DiskCache
import data.network.news.NewsNetworkSourceImpl
import data.persistence.news.NewsLocalSourceImpl
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This repo is responsible for always returning the latest headlines.
 *
 * Logic for choosing between [NewsLocalSourceImpl] and [NewsNetworkSourceImpl]
 * is managed by [OfflineFirstRepository].
 *
 * @author Dhruvaraj Nagarajan
 */
@Singleton
class NewsRepositoryImpl @Inject constructor(
    newsNetworkSourceImpl: NewsNetworkSourceImpl,
    newsLocalSourceImpl: NewsLocalSourceImpl,
    private val diskCache: DiskCache
) : OfflineFirstRepository<GetTopHeadlinesUseCase.Params, BaseResponse<NewsResponse>>(
    newsLocalSourceImpl,
    newsNetworkSourceImpl
), NewsRepository {

    override fun getConfig(): Observable<GetTopHeadlinesUseCase.Params> = Observable.create {
        val configJson = diskCache.get("config")
        val config = Gson().fromJson(configJson, GetTopHeadlinesUseCase.Params::class.java)
        if (config == null) {
            it.onNext(GetTopHeadlinesUseCase.Params(q = "News"))
            it.onComplete()
            return@create
        }
        it.onNext(config)
        it.onComplete()
    }

    override fun getTopHeadlines(params: GetTopHeadlinesUseCase.Params): Observable<BaseResponse<NewsResponse>> =
        getFromAnySource(params)
}