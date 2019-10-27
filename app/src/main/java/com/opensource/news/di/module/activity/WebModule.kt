package com.opensource.news.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.dhruvnagarajan.androidplatform.di.ViewModelKey
import com.opensource.news.view.main.MainViewModel
import com.opensource.news.view.web.WebViewActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Dhruvaraj Nagarajan
 */
@Module(includes = [CommonActivityModule::class])
abstract class WebModule {

    @Binds
    abstract fun provideActivity(webViewActivity: WebViewActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(viewModel: MainViewModel): ViewModel
}