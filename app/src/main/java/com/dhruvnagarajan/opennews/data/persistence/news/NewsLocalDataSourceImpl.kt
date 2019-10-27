package com.dhruvnagarajan.opennews.data.persistence.news

import com.dhruvnagarajan.opennews.data.persistence.LocalDataSource
import com.dhruvnagarajan.opennews.data.persistence.db.NewsDB
import com.dhruvnagarajan.opennews.data.persistence.db.entity.toEntity
import com.dhruvnagarajan.opennews.data.persistence.db.entity.toPojo
import com.dhruvnagarajan.opennews.domain.entity.NewsArticle
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.entity.NewsResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsLocalDataSourceImpl @Inject constructor(
    newsDB: NewsDB
) : LocalDataSource<NewsProfile, NewsResponse> {

    private val dao = newsDB.newsArticleDao()

    override fun get(key: NewsProfile): Observable<NewsResponse> =
        Observable.create { emitter ->
            val results = dao.getAll()

            val articles = ArrayList<NewsArticle>()

            for (result in results) {
                articles.add(result.toPojo())
            }

            emitter.onNext(
                NewsResponse(
                    null,
                    null,
                    articles
                )
            )
            emitter.onComplete()
        }

    override fun put(key: NewsProfile, value: NewsResponse) {
        for (result in value.newsArticles!!) {
            dao.insert(result.toEntity())
        }
    }

    override fun evict() = dao.truncate()
}