package data.network.news

import com.opensource.news.BuildConfig
import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import data.network.ApiService
import data.network.NetworkSource
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class NewsNetworkSourceImpl @Inject constructor(private val apiService: ApiService) :
    NetworkSource<GetTopHeadlinesUseCase.Params, BaseResponse<NewsResponse>> {

    override fun get(request: GetTopHeadlinesUseCase.Params): Observable<BaseResponse<NewsResponse>> =
        apiService.getTopHeadlines(
            request.sources,
            request.q,
            request.language,
            request.country,
            request.category,
            BuildConfig.OPENNEWSAPIKEY
        ).flatMap(Function { response ->
            return@Function if (response.isSuccessful && response.body() != null) {
                Observable.create {
                    it.onNext(
                        BaseResponse(
                            BaseResponse.Status.SUCCESS,
                            response.body()
                        )
                    )
                    it.onComplete()
                }
            } else {
                Observable.create {
                    it.onError(Throwable(response.message()))
                }
            }
        })
}