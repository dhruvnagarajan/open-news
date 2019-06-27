package com.opensource.news.di

import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import data.repository.NewsRepositoryImpl
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
class NewsUseCaseModule {

    @Provides
    @Singleton
    fun provideGetTopHeadlinesUseCase(newsRepository: NewsRepositoryImpl): GetTopHeadlinesUseCase {
        return GetTopHeadlinesUseCase(newsRepository)
    }
}