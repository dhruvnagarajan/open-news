package com.dhruvnagarajan.androidcore.persistence

/**
 * @author Dhruvaraj Nagarajan
 */
interface Cache {

    fun get(key: String): String?

    fun <V : Any> put(key: String, value: V)

    fun evict()
}