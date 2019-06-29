package data.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.util.NetworkUtils
import data.network.NetworkSource
import data.persistence.LocalSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class OfflineFirstRepository<K, V>(
    private val localSource: LocalSource<K, V>,
    private val networkSource: NetworkSource<K, V>
) {

    @Inject
    lateinit var networkUtils: NetworkUtils

    /**
     * Merges both local and network sources.
     *
     * @param key request for the source
     * @param getLatest
     *      if true,
     *          then return from local source immediately in all cases. also,
     *          if internet is available, then update local source with new data, in background
     *      else,
     *          return from local if value exists, don't check for updates from network.
     *          return from network otherwise.
     */
    fun getFromAnySource(key: K, getLatest: Boolean = false): Observable<V> {
        return if (getLatest && networkUtils.isNetworkAvailable()) {
            return localSource.get(key).mergeWith(networkSource.get(key))
                .scan { localResponse, networkResponse ->
                    localSource.put(key, networkResponse)
                    return@scan networkResponse
                }
        } else localSource.get(key).flatMap { localValue ->
            val _localResponse = localValue as BaseResponse<*>
            return@flatMap if (_localResponse.data == null) {
                networkSource.get(key).map {
                    localSource.put(key, it)
                    it
                }
            } else Observable.create {
                it.onNext(localValue)
                it.onComplete()
            }
        }
    }
}