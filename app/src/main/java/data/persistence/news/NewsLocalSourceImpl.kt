package data.persistence.news

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import data.adapter.fromStorage
import data.adapter.toStorage
import data.persistence.LocalSource
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class NewsLocalSourceImpl @Inject constructor() :
    LocalSource<GetTopHeadlinesUseCase.Params, BaseResponse<NewsResponse>> {

    private val newsPersistence by lazy { NewsPersistence() }

    override fun get(key: GetTopHeadlinesUseCase.Params): Observable<BaseResponse<NewsResponse>> =
        newsPersistence.get()

    override fun put(key: GetTopHeadlinesUseCase.Params, value: BaseResponse<NewsResponse>) {
        newsPersistence.save(value)
    }

    inner class NewsPersistence {

        fun get(): Observable<BaseResponse<NewsResponse>> =
            Observable.create {
                val queryResult = Realm.getDefaultInstance().where(NewsRO::class.java).findFirst()
                if (queryResult != null && queryResult.totalResults ?: 0 > 0) {
                    it.onNext(BaseResponse(BaseResponse.Status.SUCCESS, queryResult.fromStorage()))
                    it.onComplete()
                } else {
                    it.onNext(BaseResponse(BaseResponse.Status.ERROR, "Wow, such empty!"))
                    it.onComplete()
                }
            }

        fun save(baseResponse: BaseResponse<NewsResponse>) {
            baseResponse.data?.let { news ->
                evict()
                Realm.getDefaultInstance().executeTransaction { it.insertOrUpdate(news.toStorage()) }
            }
        }

        fun evict() {
            Realm.getDefaultInstance().executeTransaction {
                it.where(NewsRO::class.java).findAll().deleteAllFromRealm()
            }
        }
    }
}