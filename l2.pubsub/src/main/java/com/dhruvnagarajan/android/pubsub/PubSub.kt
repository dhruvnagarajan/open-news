package com.dhruvnagarajan.android.pubsub

/**
 * @author Dhruvaraj Nagarajan
 */
object PubSub {

    private val topics = HashMap<String, Topic<*>>()

    fun <T : Any> createTopic(topicName: String) {
        if (topics[topicName] != null) throw Exception("Topic already registered")
        topics[topicName] = Topic<T>(topicName)
    }

    fun getTopic(topicName: String): Topic<*> {
        if (topics[topicName] == null) throw Exception("Topic not found")
        return topics[topicName]!!
    }
}