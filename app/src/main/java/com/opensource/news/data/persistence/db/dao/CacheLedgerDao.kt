package com.opensource.news.data.persistence.db.dao

import androidx.room.*
import com.opensource.news.data.persistence.db.entity.CacheLedgerEntity

/**
 * @author Dhruvaraj Nagarajan
 */
@Dao
interface CacheLedgerDao {

    @Query("SELECT * FROM CacheLedgerEntity WHERE uid = :uid")
    fun get(uid: String): CacheLedgerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: CacheLedgerEntity)

    @Delete
    fun delete(vararg entity: CacheLedgerEntity)

    @Query("DELETE FROM CacheLedgerEntity")
    fun truncate()
}