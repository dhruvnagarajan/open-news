package com.opensource.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseViewModel : ViewModel() {

    val viewStateLiveData = MutableLiveData<ViewState>()

    init {
        viewStateLiveData.postValue(ViewState(ViewStateType.NONE))
    }

    protected fun postSuccessViewState(message: String? = null) {
        viewStateLiveData.postValue(ViewState(ViewStateType.NONE, message))
    }

    protected fun postLoadingViewState(message: String? = null) {
        viewStateLiveData.postValue(ViewState(ViewStateType.LOADING, message))
    }

    protected fun postErrorViewState(message: String?) {
        viewStateLiveData.postValue(ViewState(ViewStateType.ERROR, message))
    }

    data class ViewState(val viewStateType: ViewStateType, var message: String? = null)

    enum class ViewStateType {
        NONE, LOADING, ERROR
    }
}