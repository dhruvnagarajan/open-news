package com.dhruvnagarajan.opennews.di.module.activity

import com.dhruvnagarajan.opennews.view.main.MainActivity
import com.dhruvnagarajan.opennews.view.web.WebViewActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @author Dhruvaraj Nagarajan
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [WebModule::class])
    abstract fun contributeWebActivityInjector(): WebViewActivity
}