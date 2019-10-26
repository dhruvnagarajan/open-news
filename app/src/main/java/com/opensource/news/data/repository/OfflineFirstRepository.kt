package com.opensource.news.data.repository

import com.opensource.news.util.NetworkUtils
import com.opensource.news.data.network.NetworkSource
import com.opensource.news.data.persistence.DataSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class OfflineFirstRepository<K, V>(
    private val dataSource: DataSource<K, V>,
    private val networkSource: NetworkSource<K, V>
) {

    @Inject
    lateinit var networkUtils: NetworkUtils

    /**
     * Return from local source immediately in all cases.
     * If internet is available, then update local source with new data, in background.
     */
    fun getFromAnySource(key: K): Observable<V> {
        return if (networkUtils.isNetworkAvailable()) {
            return dataSource.get(key).mergeWith(networkSource.get(key))
                .scan { localResponse, networkResponse ->
                    dataSource.put(key, networkResponse)
                    return@scan networkResponse
                }
        } else dataSource.get(key)
    }
}