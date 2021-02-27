package com.opensource.news.di.module

import android.app.Application
import android.content.Context
import com.opensource.news.NewsApp
import dagger.Binds
import dagger.Module

/**
 * @author dhruvaraj
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideAppContext(app: NewsApp): Context

    @Binds
    abstract fun application(newsApp: NewsApp): Application
}