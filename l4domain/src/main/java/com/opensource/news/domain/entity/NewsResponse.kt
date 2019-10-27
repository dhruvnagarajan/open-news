package com.opensource.news.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Dhruvalaj Nagarajan
 */
data class NewsResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("articles")
    val newsArticles: List<NewsArticle>? = null
)

data class NewsArticle(
    @SerializedName("source")
    val newsSource: NewsSource? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("content")
    val content: String? = null
)

data class NewsSource(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)
