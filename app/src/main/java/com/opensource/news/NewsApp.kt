package com.opensource.news

import com.facebook.stetho.Stetho
import com.opensource.news.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        Stetho.initializeWithDefaults(this)
    }
}