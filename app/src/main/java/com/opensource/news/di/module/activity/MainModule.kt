package com.opensource.news.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.opensource.news.di.ViewModelKey
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.view.configure.ConfigurationViewModel
import com.opensource.news.view.configure.ConfigureTopicBottomSheet
import com.opensource.news.view.main.MainActivity
import com.opensource.news.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import data.repository.NewsRepositoryImpl

/**
 * @author dhruvaraj
 */
@Module(includes = [CommonActivityModule::class])
abstract class MainModule {

    @Binds
    abstract fun provideActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun provideConfigureBottomSheet(): ConfigureTopicBottomSheet

    @Binds
    @IntoMap
    @ViewModelKey(ConfigurationViewModel::class)
    abstract fun provideConfigViewModel(configurationViewModel: ConfigurationViewModel): ViewModel

    @Binds
    abstract fun newsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}