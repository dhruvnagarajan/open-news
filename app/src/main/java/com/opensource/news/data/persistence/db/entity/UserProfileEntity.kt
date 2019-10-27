package com.opensource.news.data.persistence.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.opensource.news.domain.entity.UserProfile

/**
 * @author Dhruvaraj Nagarajan
 */
@Entity
data class UserProfileEntity(
    @PrimaryKey
    val uid: Long,
    val name: String
)

fun UserProfileEntity.toPOJO() =
    UserProfile(uid, name)

fun UserProfile.toEntity(uid: Long) =
    UserProfileEntity(uid, name)