package com.dhruvnagarajan.opennews.di.module.activity

import com.dhruvnagarajan.opennews.view.progressbottomsheet.ProgressBottomSheet
import com.dhruvnagarajan.opennews.view.progressbottomsheet.ViewStatePrompt
import com.dhruvnagarajan.opennews.view.progressbottomsheet.ViewStatePromptImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class CommonActivityModule {

    @Binds
    abstract fun provideViewStatePrompt(viewStateDialogImpl: ViewStatePromptImpl): ViewStatePrompt

    @ContributesAndroidInjector
    abstract fun provideProgressBottomSheet(): ProgressBottomSheet
}