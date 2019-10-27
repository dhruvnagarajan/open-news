package com.opensource.news.domain.entity

/**
 * @author Dhruvaraj Nagarajan
 */
data class NewsProfile(
    val sources: String? = null,
    val q: String? = null,
    val language: String? = null,
    val country: String? = null,
    val category: String? = null
)