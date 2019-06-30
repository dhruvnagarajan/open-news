package com.opensource.news.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.opensource.news.di.ViewModelKey
import com.opensource.news.view.main.MainActivity
import com.opensource.news.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Dhruvaraj Nagarajan
 */
@Module(includes = [CommonActivityModule::class])
abstract class MainModule {

    @Binds
    abstract fun provideActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}