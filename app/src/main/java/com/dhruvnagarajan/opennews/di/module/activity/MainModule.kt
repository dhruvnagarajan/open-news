package com.dhruvnagarajan.opennews.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.dhruvnagarajan.androidplatform.di.ViewModelKey
import com.dhruvnagarajan.opennews.router.RootRouter
import com.dhruvnagarajan.opennews.router.RootRouterImpl
import com.dhruvnagarajan.opennews.view.CreateProfileFragment
import com.dhruvnagarajan.opennews.view.NewsFragment
import com.dhruvnagarajan.opennews.view.ProfileFragment
import com.dhruvnagarajan.opennews.view.main.MainActivity
import com.dhruvnagarajan.opennews.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author Dhruvaraj Nagarajan
 */
@Module(includes = [CommonActivityModule::class])
abstract class MainModule {

    @Binds
    abstract fun provideActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    abstract fun provideRootRouter(rootRouterImpl: RootRouterImpl): RootRouter

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun profileCreateFragment(): CreateProfileFragment
}