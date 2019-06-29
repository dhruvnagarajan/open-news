package com.opensource.news.di.module

import androidx.appcompat.app.AppCompatActivity
import com.opensource.news.view.progressbottomsheet.ProgressBottomSheet
import com.opensource.news.view.progressbottomsheet.ViewStatePrompt
import com.opensource.news.view.progressbottomsheet.ViewStatePromptImpl
import com.opensource.news.view.web.WebViewActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Dhruvaraj Nagarajan
 */
@Module
abstract class WebModule {

    @Binds
    abstract fun provideActivity(webViewActivity: WebViewActivity): AppCompatActivity

    @Binds
    abstract fun provideViewStatePrompt(viewStateDialogImpl: ViewStatePromptImpl): ViewStatePrompt

    @ContributesAndroidInjector
    abstract fun provideProgressBottomSheet(): ProgressBottomSheet
}