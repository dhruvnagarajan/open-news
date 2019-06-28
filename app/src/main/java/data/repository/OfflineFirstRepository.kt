package data.repository

import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import data.network.NetworkSource
import data.persistence.LocalSource
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class OfflineFirstRepository<K, V>(
    private val localSource: LocalSource<K, V>,
    private val networkSource: NetworkSource<K, V>
) {

    fun getFromAnySource(key: K): Observable<V> {
        return localSource.get(key).flatMap { localValue ->
            val localResponse = localValue as BaseResponse<NewsResponse>
            return@flatMap if (localResponse.data != null) {
                Observable.create {
                    it.onNext(localValue)
                    it.onComplete()
                }
            } else networkSource.get(key).map { apiResponse ->
                localSource.put(key, apiResponse)
                return@map apiResponse
            }
        }
    }
}