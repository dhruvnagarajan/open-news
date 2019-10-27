package com.dhruvnagarajan.androidplatform.util.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
 * misc
 */
fun <T : Any> Observable<T>.attachObserver(observer: Observer<T>) =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)

fun <T : Any> Observable<T>.attachObserver(
    subscriberOnNext: (t: T) -> Unit,
    subscriberOnError: (e: Throwable) -> Unit
) =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            subscriberOnNext,
            subscriberOnError
        )

/*
 * view model
 */
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.getFragmentViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.getActivityViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T =
    ViewModelProviders.of(activity!!, viewModelFactory)[T::class.java]