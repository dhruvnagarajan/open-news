package com.opensource.news.view.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
@SuppressLint("CheckResult")
class MainViewModel @Inject constructor(private val getTopHeadlines: GetTopHeadlinesUseCase) : BaseViewModel() {

    val newsLiveData by lazy { MutableLiveData<NewsResponse>() }

    fun fetchNews(params: GetTopHeadlinesUseCase.Params) {
        postLoadingViewState("Fetching Top Highlights...")
        getTopHeadlines.execute(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsLiveData.postValue(it.data)
                postSuccessViewState()
            }, { postErrorViewState(it.message) })
    }
}