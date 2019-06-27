package com.opensource.news.di

import androidx.lifecycle.ViewModel
import com.opensource.news.main.MainActivity
import com.opensource.news.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap

/**
 * @author Dhruvaraj Nagarajan
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}