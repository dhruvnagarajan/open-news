package com.opensource.news.domain.entity

data class NewsRequest(
    val sources: String? = null,
    val q: String? = null,
    val language: String? = null,
    val country: String? = null,
    val category: String? = null
)