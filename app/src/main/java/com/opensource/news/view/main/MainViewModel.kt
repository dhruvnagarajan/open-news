package com.opensource.news.view.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.domain.model.NewsResponse
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.view.base.BaseViewModel
import data.repository.ImageRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
@SuppressLint("CheckResult")
class MainViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase,
    private val imageRepository: ImageRepository
) : BaseViewModel() {

    val newsLiveData by lazy { MutableLiveData<NewsResponse>() }
    val bitmapLiveDataHash by lazy { HashMap<String, MutableLiveData<Bitmap>>() }

    fun fetchNews(params: GetTopHeadlinesUseCase.Params) {
        postLoadingViewState("Fetching Top Highlights...")
        getTopHeadlines.execute(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == BaseResponse.Status.SUCCESS) {
                    newsLiveData.postValue(it.data)
                    postSuccessViewState()
                } else postErrorViewState(it.message)
            }, { postErrorViewState(it.message) })
    }

    fun getImg(url: String): MutableLiveData<Bitmap> {
        if (bitmapLiveDataHash[url] == null) {
            val bitmapLiveData = MutableLiveData<Bitmap>()
            imageRepository.getImage(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { bitmapLiveData.postValue(it) }
            return bitmapLiveData
        } else return bitmapLiveDataHash[url]!!
    }
}