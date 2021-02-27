package com.opensource.news.view.configure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opensource.news.domain.repository.NewsRepository
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.util.DiskCache
import com.opensource.news.view.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class ConfigurationViewModel @Inject constructor(
    private val diskCache: DiskCache,
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    fun getConfiguration(): LiveData<State<GetTopHeadlinesUseCase.Params>> {
        val liveData = MutableLiveData<State<GetTopHeadlinesUseCase.Params>>()
        newsRepository.getConfig()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = State(data = it)
            }, {
                liveData.value = State(message = it.message)
            })
        return liveData
    }

    fun saveConfiguration(params: GetTopHeadlinesUseCase.Params): LiveData<State<String>> {
        val liveData = MutableLiveData<State<String>>()
        Completable.create {
            diskCache.put("config", params)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
            .subscribe({
                liveData.postValue(State(data = "Saved"))
            }, {
                liveData.postValue(State(message = it.message))
            })
        return liveData
    }
}