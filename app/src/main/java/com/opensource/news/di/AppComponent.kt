package com.opensource.news.di

import com.opensource.news.NewsApp
import com.opensource.news.di.module.AppModule
import com.opensource.news.di.module.DataModule
import com.opensource.news.di.module.NetworkModule
import com.opensource.news.di.module.activity.ActivityModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AppModule::class,
        DataModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>()
}