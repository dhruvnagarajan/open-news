package com.opensource.news.di.module

import android.content.Context
import com.opensource.news.NewsApplication
import com.dhruvnagarajan.androidplatform.di.Application
import dagger.Binds
import dagger.Module

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class AppModule {

    @Binds
    @Application
    abstract fun provideAppContext(application: NewsApplication): Context
}