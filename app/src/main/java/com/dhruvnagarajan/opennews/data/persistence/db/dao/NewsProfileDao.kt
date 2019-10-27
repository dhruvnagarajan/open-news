package com.dhruvnagarajan.opennews.data.persistence.db.dao

import androidx.room.*
import com.dhruvnagarajan.opennews.data.persistence.db.entity.NewsProfileEntity

/**
 * @author Dhruvaraj Nagarajan
 */
@Dao
interface NewsProfileDao {

    @Query("SELECT * FROM NewsProfileEntity")
    fun getAll(): List<NewsProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: NewsProfileEntity)

    @Delete
    fun delete(vararg entity: NewsProfileEntity)

    @Query("DELETE FROM NewsProfileEntity")
    fun truncate()
}