package com.opensource.news.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.opensource.news.di.ViewModelKey
import com.opensource.news.view.main.MainActivity
import com.opensource.news.view.main.MainViewModel
import com.opensource.news.view.progressbottomsheet.ProgressBottomSheet
import com.opensource.news.view.progressbottomsheet.ViewStatePrompt
import com.opensource.news.view.progressbottomsheet.ViewStatePromptImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class MainModule {

    @Binds
    abstract fun provideActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun provideViewStatePrompt(viewStateDialogImpl: ViewStatePromptImpl): ViewStatePrompt

    @ContributesAndroidInjector
    abstract fun provideProgressBottomSheet(): ProgressBottomSheet
}