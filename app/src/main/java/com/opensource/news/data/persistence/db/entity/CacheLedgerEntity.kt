package com.opensource.news.data.persistence.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.opensource.news.domain.entity.CacheLedger

/**
 * @author Dhruvaraj Nagarajan
 */
@Entity
data class CacheLedgerEntity(
    @PrimaryKey
    val uid: String,
    val lastUpdateTime: Long,
    val errorTime: Long?,
    val errorMessage: String?
)

fun CacheLedgerEntity.toPOJO() =
    CacheLedger(uid, lastUpdateTime, errorTime, errorMessage)

fun CacheLedger.toEntity() =
    CacheLedgerEntity(key, lastUpdateTime, errorTime, errorMessage)