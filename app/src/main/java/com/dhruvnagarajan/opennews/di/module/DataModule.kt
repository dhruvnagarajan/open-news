package com.dhruvnagarajan.opennews.di.module

import android.content.Context
import androidx.room.Room
import com.dhruvnagarajan.androidcore.persistence.Cache
import com.dhruvnagarajan.androidcore.persistence.DiskCache
import com.dhruvnagarajan.androidplatform.di.Application
import com.dhruvnagarajan.androidplatform.di.Disk
import com.dhruvnagarajan.opennews.data.persistence.db.CacheLedgerDB
import com.dhruvnagarajan.opennews.data.persistence.db.NewsDB
import com.dhruvnagarajan.opennews.data.repository.NewsRepositoryImpl
import com.dhruvnagarajan.opennews.data.repository.ProfileRepositoryImpl
import com.dhruvnagarajan.opennews.domain.repository.NewsRepository
import com.dhruvnagarajan.opennews.domain.repository.ProfileRepository
import com.dhruvnagarajan.opennews.domain.usecase.HeadlinesUseCase
import com.dhruvnagarajan.opennews.domain.usecase.ProfileUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
class DataModule {

    @Provides
    @Singleton
    @Disk
    fun provideDiskCache(
        @Application context: Context
    ): Cache = DiskCache(context)

    @Provides
    @Singleton
    fun provideCacheLedgerDB(@Application context: Context): CacheLedgerDB =
        Room.databaseBuilder(
            context,
            CacheLedgerDB::class.java,
            CacheLedgerDB::class.java.name
        ).build()

    @Provides
    @Singleton
    fun provideNewsDB(@Application context: Context): NewsDB =
        Room.databaseBuilder(
            context,
            NewsDB::class.java,
            NewsDB::class.java.name
        ).build()

    @Provides
    fun newsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository =
        newsRepositoryImpl

    @Provides
    @Singleton
    fun headlinesUseCase(newsRepository: NewsRepository): HeadlinesUseCase {
        return HeadlinesUseCase(newsRepository)
    }

    @Provides
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository =
        profileRepositoryImpl

    @Provides
    @Singleton
    fun profilesUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(profileRepository)
    }
}