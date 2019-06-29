package com.opensource.news.di.module

import com.opensource.news.view.main.MainActivity
import com.opensource.news.view.web.WebViewActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class ContributeActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [WebModule::class])
    abstract fun contributeWebActivityInjector(): WebViewActivity
}