package com.opensource.news.data.repository

import android.content.Context
import com.opensource.news.data.network.RemoteDataSource
import com.opensource.news.data.persistence.LocalDataSource
import com.opensource.news.data.persistence.db.CacheLedgerDB
import com.opensource.news.data.persistence.db.entity.toEntity
import com.opensource.news.domain.entity.CacheLedger
import com.opensource.news.util.NetworkUtils
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class OfflineFirstRepository<K, V>(
    val context: Context,
    cacheLedgerDB: CacheLedgerDB,
    private val localLocalDataSource: LocalDataSource<K, V>,
    private val remoteDataSource: RemoteDataSource<K, V>
) {

    private val dao = cacheLedgerDB.cacheDao()

    fun getFromLocalOrRemote(key: K, forceUpdate: Boolean = false): Observable<V> {
        return if (forceUpdate) {
            return remoteDataSource.get(key)
                .map {
                    dao.insert(
                        CacheLedger(
                            key.toString(),
                            System.currentTimeMillis(),
                            null,
                            null
                        ).toEntity()
                    )
                    it
                }
                .map { remoteResponse ->
                    localLocalDataSource.put(key, remoteResponse)
                    remoteResponse
                }
        } else {
            if (NetworkUtils.isNetworkAvailable(context)) {
                return localLocalDataSource.get(key).mergeWith(remoteDataSource.get(key))
                    .scan { localResponse, remoteResponse ->
                        localLocalDataSource.put(key, remoteResponse)

                        dao.insert(
                            CacheLedger(
                                key.toString(),
                                System.currentTimeMillis(),
                                null,
                                null
                            ).toEntity()
                        )

                        remoteResponse
                    }
            } else localLocalDataSource.get(key)
        }
    }
}