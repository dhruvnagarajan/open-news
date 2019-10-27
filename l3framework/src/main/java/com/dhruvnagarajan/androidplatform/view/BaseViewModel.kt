package com.dhruvnagarajan.androidplatform.view

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseViewModel : ViewModel() {

    val viewStateObservable: Subject<ViewState> = ReplaySubject.create()

    sealed class ViewState {
        object Success : ViewState()
        data class Loading(val message: String) : ViewState()
        data class Error(val message: String) : ViewState() {
            constructor(t: Throwable) : this(t.message!!)
        }
    }
}