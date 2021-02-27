package com.opensource.news.di.module

import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import data.repository.NewsRepositoryImpl
import javax.inject.Singleton

/**
 * @author dhruvaraj
 */
@Module
class NewsUseCaseModule {

    @Provides
    @Singleton
    fun provideGetTopHeadlinesUseCase(newsRepository: NewsRepositoryImpl): GetTopHeadlinesUseCase {
        return GetTopHeadlinesUseCase(newsRepository)
    }
}