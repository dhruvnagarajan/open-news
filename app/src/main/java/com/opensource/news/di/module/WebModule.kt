package com.opensource.news.di.module

import androidx.lifecycle.ViewModel
import com.opensource.news.view.base.BaseViewModel
import dagger.Module
import dagger.Provides

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
class WebModule {

    @Provides
    fun provideViewModel(baseViewModel: BaseViewModel): ViewModel {
        return BaseViewModel()
    }
}