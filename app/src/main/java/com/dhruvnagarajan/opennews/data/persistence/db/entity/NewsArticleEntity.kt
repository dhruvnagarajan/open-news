package com.dhruvnagarajan.opennews.data.persistence.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhruvnagarajan.opennews.domain.entity.NewsArticle
import com.dhruvnagarajan.opennews.domain.entity.NewsSource

/**
 * @author Dhruvaraj Nagarajan
 */
@Entity
data class NewsArticleEntity(
    @PrimaryKey
    val uid: String,
    val sourceId: String? = null,
    val sourceName: String? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

fun NewsArticleEntity.toPojo() =
    NewsArticle(
        NewsSource(sourceId, sourceName),
        author, title, description, url, urlToImage, publishedAt, content
    )

fun NewsArticle.toEntity() =
    NewsArticleEntity(
        "${newsSource?.id} $publishedAt",
        newsSource?.id,
        newsSource?.name,
        author, title, description, url, urlToImage, publishedAt, content
    )