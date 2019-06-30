package com.opensource.news.di

import com.opensource.news.NewsApp
import com.opensource.news.di.module.AppModule
import com.opensource.news.di.module.activity.ContributeActivityModule
import com.opensource.news.di.module.NetworkModule
import com.opensource.news.di.module.NewsUseCaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Dhruvaraj Nagarajan
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContributeActivityModule::class,
        AppModule::class,
        NetworkModule::class,
        NewsUseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>()
}