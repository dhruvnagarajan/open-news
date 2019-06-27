package data.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import data.TempConstants
import data.network.ApiService
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
class NewsRepositoryImpl @Inject constructor() : NewsRepository {

    @Inject
    lateinit var apiService: ApiService

    override fun getTopHeadlines(params: GetTopHeadlinesUseCase.Params): Observable<BaseResponse<NewsResponse>> {
        return apiService.getTopHeadlines(
            params.sources,
            params.q,
            params.language,
            params.country,
            params.category,
            TempConstants.API_KEY
        ).flatMap(Function { response ->
            return@Function if (response.isSuccessful && response.body() != null) {
                Observable.create<BaseResponse<NewsResponse>> {
                    it.onNext(
                        BaseResponse(
                            BaseResponse.Status.SUCCESS,
                            response.message(),
                            response.body()
                        )
                    )
                    it.onComplete()
                }
            } else {
                Observable.create<BaseResponse<NewsResponse>> {
                    it.onError(Throwable(response.message()))
                }
            }
        })
    }
}