package com.dhruvnagarajan.opennews.data.persistence.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile

/**
 * @author Dhruvaraj Nagarajan
 */
@Entity
data class NewsProfileEntity(
    @PrimaryKey
    val uid: Long,
    val sources: String? = null,
    val query: String? = null,
    val language: String? = null,
    val country: String? = null,
    val category: String? = null
)

fun NewsProfileEntity.toPOJO() =
    NewsProfile(sources, query, language, country, category)

fun NewsProfile.toEntity(uid: Long) =
    NewsProfileEntity(
        uid,
        sources,
        q,
        language,
        country,
        category
    )