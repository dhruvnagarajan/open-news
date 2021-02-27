package com.opensource.news.view.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
@SuppressLint("CheckResult")
class MainViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase
) : BaseViewModel() {

    val newsLiveData by lazy { MutableLiveData<NewsResponse>() }

    fun fetchNews() {
        postLoadingViewState("Fetching Top Highlights...")
        getTopHeadlines.execute(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == BaseResponse.Status.SUCCESS) {
                    newsLiveData.postValue(it.data)
                    postSuccessViewState()
                } else
                // Observer.onError() is not used for this response in repo because
                // it is using Observable.merge()
                // as a result, we have to consume empty data in Observer.onNext() response
                    postErrorViewState(it.message)
            }, { postErrorViewState(it.message) })
    }
}