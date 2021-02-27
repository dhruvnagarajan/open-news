package data.repository

import com.opensource.news.util.NetworkUtils
import data.network.NetworkSource
import data.persistence.LocalSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
abstract class OfflineFirstRepository<K, V>(
    private val localSource: LocalSource<K, V>,
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
            return localSource.get(key).mergeWith(networkSource.get(key))
                .scan { localResponse, networkResponse ->
                    localSource.put(key, networkResponse)
                    return@scan networkResponse
                }
        } else localSource.get(key)
    }
}