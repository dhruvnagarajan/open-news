package com.dhruvnagarajan.android.pubsub

import androidx.room.Entity

/**
 * @author Dhruvaraj Nagarajan
 */
@Entity
data class Event<T : Any>(
    val payload: T,
    val status: Int = 200,
    val message: String? = null
)