package com.opensource.news.di.module

import com.opensource.news.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}