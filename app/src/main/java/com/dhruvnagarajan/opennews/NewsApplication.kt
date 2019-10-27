package com.dhruvnagarajan.opennews

import com.facebook.stetho.Stetho
import com.dhruvnagarajan.opennews.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}