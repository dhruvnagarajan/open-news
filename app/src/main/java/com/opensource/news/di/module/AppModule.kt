package com.opensource.news.di.module

import android.content.Context
import com.opensource.news.NewsApp
import dagger.Binds
import dagger.Module

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideAppContext(app: NewsApp): Context
}