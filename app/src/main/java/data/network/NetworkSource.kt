package data.network

import io.reactivex.Observable

/**
 * @author dhruvaraj
 */
interface NetworkSource<K, V> {

    fun get(request: K): Observable<V>
}