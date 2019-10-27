package com.opensource.news.data.persistence.db.dao

import androidx.room.*
import com.opensource.news.data.persistence.db.entity.NewsArticleEntity

/**
 * @author Dhruvaraj Nagarajan
 */
@Dao
interface NewsArticleDao {

    @Query("SELECT * FROM NewsArticleEntity")
    fun getAll(): List<NewsArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: NewsArticleEntity)

    @Delete
    fun delete(entity: NewsArticleEntity)

    @Query("DELETE FROM NewsArticleEntity")
    fun truncate()
}