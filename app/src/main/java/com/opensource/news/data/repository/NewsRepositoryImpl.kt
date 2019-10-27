package com.opensource.news.data.repository

import android.content.Context
import com.dhruvnagarajan.androidplatform.di.Application
import com.opensource.news.data.network.news.NewsRemoteDataSourceImpl
import com.opensource.news.data.persistence.db.CacheLedgerDB
import com.opensource.news.data.persistence.db.entity.toEntity
import com.opensource.news.data.persistence.db.entity.toPOJO
import com.opensource.news.data.persistence.news.NewsLocalDataSourceImpl
import com.opensource.news.domain.entity.CacheLedger
import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.domain.entity.NewsResponse
import com.opensource.news.domain.repository.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsRepositoryImpl @Inject constructor(
    @Application context: Context,
    val cacheLedgerDB: CacheLedgerDB,
    newsNetworkSourceImpl: NewsRemoteDataSourceImpl,
    newsLocalSourceImpl: NewsLocalDataSourceImpl
) : OfflineFirstRepository<NewsProfile, NewsResponse>(
    context, cacheLedgerDB, newsLocalSourceImpl, newsNetworkSourceImpl
), NewsRepository {

    override fun getTopHeadlines(profile: NewsProfile): Observable<NewsResponse> =
        getFromLocalOrRemote(profile)

    override fun getCacheLedgerEntry(key: String): Observable<CacheLedger> =
        Observable.create { emitter ->
            val ledgerEntry = cacheLedgerDB.cacheDao().get(NewsProfile::class.java.name)

            if (ledgerEntry == null) {
                val ledgerEntry = CacheLedger(
                    key,
                    System.currentTimeMillis() - 30 * 1000 * 60,
                    null,
                    null
                )
                cacheLedgerDB.cacheDao().insert(ledgerEntry.toEntity())
                emitter.onNext(ledgerEntry)
            } else {
                emitter.onNext(ledgerEntry.toPOJO())
            }

            emitter.onComplete()
        }
}