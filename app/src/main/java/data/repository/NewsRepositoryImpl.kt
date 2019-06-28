package data.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import data.network.news.NewsNetworkSourceImpl
import data.persistence.news.NewsLocalSourceImpl
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
class NewsRepositoryImpl @Inject constructor(
    newsNetworkSourceImpl: NewsNetworkSourceImpl, newsLocalSourceImpl: NewsLocalSourceImpl
) : OfflineFirstRepository<GetTopHeadlinesUseCase.Params, BaseResponse<NewsResponse>>(
    newsLocalSourceImpl, newsNetworkSourceImpl
), NewsRepository {

    override fun getTopHeadlines(params: GetTopHeadlinesUseCase.Params):
            Observable<BaseResponse<NewsResponse>> = getFromAnySource(params)
}