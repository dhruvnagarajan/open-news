package com.opensource.news.domain.entity

/**
 * @author Dhruvaraj Nagarajan
 */
data class CacheLedger(
    val key: String,
    val lastUpdateTime: Long,
    val errorTime: Long?,
    val errorMessage: String?
)