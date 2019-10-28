package com.dhruvnagarajan.android.pubsub

import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject

/**
 * @author Dhruvaraj Nagarajan
 */
class Topic<T : Any>(val key: String) {

    val bridge: Subject<Event<T>> = ReplaySubject.create()

    val producers = ArrayList<Producer<Event<T>>>()

    val consumers = ArrayList<Consumer<Event<T>>>()

    fun registerProducer(topicName: String, producer: Producer<Event<T>>) {
        producers.add(producer)
    }

    fun registerConsumer(topicName: String, consumer: Consumer<Event<T>>) {
        consumers.add(consumer)
    }
}