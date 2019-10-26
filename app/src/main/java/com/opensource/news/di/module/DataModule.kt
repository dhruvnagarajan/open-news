package com.opensource.news.di.module

import com.opensource.news.data.repository.NewsRepositoryImpl
import com.opensource.news.data.repository.ProfileRepositoryImpl
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.domain.repository.ProfileRepository
import com.opensource.news.domain.usecase.HeadlinesUseCase
import com.opensource.news.domain.usecase.ProfileUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
class DataModule {

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