package com.dhruvnagarajan.opennews.data.repository

import android.content.Context
import com.dhruvnagarajan.androidplatform.di.Application
import com.dhruvnagarajan.opennews.data.network.news.NewsRemoteDataSourceImpl
import com.dhruvnagarajan.opennews.data.persistence.db.CacheLedgerDB
import com.dhruvnagarajan.opennews.data.persistence.db.entity.toEntity
import com.dhruvnagarajan.opennews.data.persistence.db.entity.toPOJO
import com.dhruvnagarajan.opennews.data.persistence.news.NewsLocalDataSourceImpl
import com.dhruvnagarajan.opennews.domain.entity.CacheLedger
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.domain.entity.NewsResponse
import com.dhruvnagarajan.opennews.domain.repository.NewsRepository
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