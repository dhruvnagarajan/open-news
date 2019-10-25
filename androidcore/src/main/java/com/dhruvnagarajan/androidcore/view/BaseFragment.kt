package com.dhruvnagarajan.androidcore.view

import com.dhruvnagarajan.androidcore.util.ext.showToast
import dagger.android.support.DaggerFragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseFragment : DaggerFragment() {

    private val disposables = ArrayList<Disposable>()

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

    protected open fun postSuccess() {}

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
