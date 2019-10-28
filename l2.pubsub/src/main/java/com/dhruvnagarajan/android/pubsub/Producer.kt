package com.dhruvnagarajan.android.pubsub

/**
 * @author Dhruvaraj Nagarajan
 */
class Producer<T : Any>(private val topic: Topic<T>) {

    fun publish(t: T) {
        topic.bridge.onNext(Event(t))
    }

    private fun cache(t: T) {

    }
}