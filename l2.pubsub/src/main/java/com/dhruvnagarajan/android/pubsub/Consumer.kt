package com.dhruvnagarajan.android.pubsub

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class Consumer<T : Any> {

    abstract fun consume(t: T)
}