package com.opensource.news.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
open class BaseViewModel @Inject constructor() : ViewModel() {

    val viewStateLiveData = MutableLiveData<ViewState>()

    init {
        viewStateLiveData.postValue(ViewState(ViewStateType.NONE))
    }

    protected fun postSuccessViewState(message: String? = null) {
        viewStateLiveData.postValue(
            ViewState(
                ViewStateType.NONE,
                message
            )
        )
    }

    protected fun postLoadingViewState(message: String? = null) {
        // using set value to immediately show loading UI
        viewStateLiveData.value = ViewState(
            ViewStateType.LOADING,
            message
        )
    }

    protected fun postErrorViewState(message: String?) {
        viewStateLiveData.postValue(
            ViewState(
                ViewStateType.ERROR,
                message
            )
        )
    }

    data class ViewState(val viewStateType: ViewStateType, var message: String? = null)

    enum class ViewStateType {
        NONE, LOADING, ERROR
    }

    data class State<T : Any>(
        val isLoading: Boolean = false,
        val data: T? = null,
        val message: String? = null
    )
}