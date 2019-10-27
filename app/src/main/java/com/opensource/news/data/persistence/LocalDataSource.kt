package com.opensource.news.data.persistence

import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface LocalDataSource<K, V> {

    fun get(key: K): Observable<V>

    fun put(key: K, value: V)

    fun evict()
}