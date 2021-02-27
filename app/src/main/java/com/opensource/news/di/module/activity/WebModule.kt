package com.opensource.news.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.opensource.news.di.ViewModelKey
import com.opensource.news.view.base.BaseViewModel
import com.opensource.news.view.web.WebViewActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author dhruvaraj
 */
@Module(includes = [CommonActivityModule::class])
abstract class WebModule {

    @Binds
    abstract fun provideActivity(webViewActivity: WebViewActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    abstract fun provideMainViewModel(baseViewModel: BaseViewModel): ViewModel
}