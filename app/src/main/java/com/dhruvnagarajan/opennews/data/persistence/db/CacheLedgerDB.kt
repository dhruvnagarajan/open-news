package com.dhruvnagarajan.opennews.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dhruvnagarajan.opennews.data.persistence.db.dao.CacheLedgerDao
import com.dhruvnagarajan.opennews.data.persistence.db.entity.CacheLedgerEntity

/**
 * @author Dhruvaraj Nagarajan
 */
@Database(
    version = 1, exportSchema = false,
    entities = [
        CacheLedgerEntity::class
    ]
)
abstract class CacheLedgerDB : RoomDatabase() {

    abstract fun cacheDao(): CacheLedgerDao
}