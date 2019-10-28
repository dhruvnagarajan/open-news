package com.dhruvnagarajan.androidplatform.view

import android.app.Dialog
import android.os.Bundle
import com.dhruvnagarajan.androidplatform.util.ext.showToast

import com.google.android.material.bottomsheet.BottomSheetDialog

import dagger.android.support.DaggerAppCompatDialogFragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseBottomSheetFragment : DaggerAppCompatDialogFragment() {

    private val disposables = ArrayList<Disposable>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(context!!, theme)

    protected fun <T : Any> getBaseObserver(
        subscriberOnNext: (t: T) -> Unit,
        subscriberOnError: (e: Throwable) -> Unit = {
            postError(it)
        }
    ): Observer<T> =
        object : Observer<T> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
                postLoading()
            }

            override fun onNext(t: T) {
                postSuccess()
                subscriberOnNext(t)
            }

            override fun onError(e: Throwable) {
                postError(e)
                subscriberOnError(e)
            }
        }

    protected open fun postSuccess(message: String? = null) {
        showToast(message)
    }

    protected open fun postLoading() {}

    protected open fun postError(message: String) = showToast(message)

    protected open fun postError(e: Throwable) = showToast(e.message)

    override fun onDestroy() {
        for (disposable in disposables) {
            if (!disposable.isDisposed)
                disposable.dispose()
        }

        super.onDestroy()
    }
}

