package com.opensource.news.data.persistence.news

import com.opensource.news.data.persistence.DataSource
import com.opensource.news.data.persistence.fromStorage
import com.opensource.news.data.persistence.toStorage
import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.domain.entity.NewsResponse
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsDataSourceImpl @Inject constructor() : DataSource<NewsRequest, NewsResponse> {

    private val newsPersistence by lazy { NewsPersistence() }

    override fun get(key: NewsRequest): Observable<NewsResponse> =
        newsPersistence.get()

    override fun put(key: NewsRequest, value: NewsResponse) =
        newsPersistence.save(value)

    override fun evict() = newsPersistence.evict()

    inner class NewsPersistence {

        fun get(): Observable<NewsResponse> =
            Observable.create {
                val queryResult = Realm.getDefaultInstance().where(NewsRO::class.java).findFirst()
                if (queryResult != null && queryResult.totalResults ?: 0 > 0) {
                    it.onNext(queryResult.fromStorage())
                    it.onComplete()
                } else {
                    it.onError(Throwable("Wow, such empty!"))
                }
            }

        fun save(newsResponse: NewsResponse) {
            newsResponse.let { news ->
                evict()
                Realm.getDefaultInstance().executeTransaction {
                    it.insertOrUpdate(news.toStorage())
                }
            }
        }

        fun evict() {
            Realm.getDefaultInstance().executeTransaction {
                it.where(NewsRO::class.java).findAll().deleteAllFromRealm()
            }
        }
    }
}