package com.dhruvnagarajan.opennews.di

import com.dhruvnagarajan.opennews.NewsApplication
import com.dhruvnagarajan.opennews.di.module.AppModule
import com.dhruvnagarajan.opennews.di.module.DataModule
import com.dhruvnagarajan.opennews.di.module.NetworkModule
import com.dhruvnagarajan.opennews.di.module.activity.ActivityModule
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
interface AppComponent : AndroidInjector<NewsApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApplication>()
}