package data.persistence

import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface LocalSource<K, V> {

    fun get(key: K): Observable<V>

    fun put(key: K, value: V)
}