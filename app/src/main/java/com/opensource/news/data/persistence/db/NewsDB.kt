package com.opensource.news.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.opensource.news.data.persistence.db.dao.NewsArticleDao
import com.opensource.news.data.persistence.db.dao.NewsProfileDao
import com.opensource.news.data.persistence.db.entity.NewsArticleEntity
import com.opensource.news.data.persistence.db.entity.NewsProfileEntity

/**
 * @author Dhruvaraj Nagarajan
 */
@Database(
    version = 1, exportSchema = false,
    entities = [
        NewsArticleEntity::class,
        NewsProfileEntity::class]
)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao

    abstract fun newsProfileDao(): NewsProfileDao
}