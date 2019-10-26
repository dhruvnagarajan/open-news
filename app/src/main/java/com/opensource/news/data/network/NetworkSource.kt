package com.opensource.news.data.network

import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
interface NetworkSource<K, V> {

    fun get(request: K): Observable<V>
}