package com.opensource.news.di

import com.opensource.news.NewsApp
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
        NetworkModule::class,
        NewsUseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>()
}