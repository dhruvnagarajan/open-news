package com.opensource.news.di.module.activity

import com.opensource.news.view.progressbottomsheet.ProgressBottomSheet
import com.opensource.news.view.progressbottomsheet.ViewStatePrompt
import com.opensource.news.view.progressbottomsheet.ViewStatePromptImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author dhruvaraj
 */
@Module
abstract class CommonActivityModule {

    @Binds
    abstract fun provideViewStatePrompt(viewStateDialogImpl: ViewStatePromptImpl): ViewStatePrompt

    @ContributesAndroidInjector
    abstract fun provideProgressBottomSheet(): ProgressBottomSheet
}