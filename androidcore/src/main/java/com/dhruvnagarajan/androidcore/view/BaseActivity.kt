package com.dhruvnagarajan.androidcore.view

import android.app.ProgressDialog
import com.dhruvnagarajan.androidcore.util.ext.attachObserver
import com.dhruvnagarajan.androidcore.util.ext.showToast
import com.dhruvnagarajan.nav.popFragmentSync
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    private val disposables = ArrayList<Disposable>()
    private var progressDialog: ProgressDialog? = null

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
                showLoading(BaseViewModel.ViewState.Loading("Loading..."))
            }

            override fun onNext(t: T) {
                showSuccess(BaseViewModel.ViewState.Success)
                subscriberOnNext(t)
            }

            override fun onError(e: Throwable) {
                showError(BaseViewModel.ViewState.Error("Something went wrong!"))
                subscriberOnError(e)
            }
        }

    protected fun observeViewState(viewModel: BaseViewModel) {
        viewModel.viewStateObservable
            .attachObserver(getBaseObserver(
                {
                    when (it) {
                        is BaseViewModel.ViewState.Success -> {
                            showSuccess(it)
                        }
                        is BaseViewModel.ViewState.Loading -> {
                            showLoading(it)
                        }
                        is BaseViewModel.ViewState.Error -> {
                            showError(it)
                        }
                    }
                },
                { postError(it) }
            ))
    }

    open fun showSuccess(it: BaseViewModel.ViewState.Success) {
        hideLoading()
        hideAlert()
    }

    open fun showLoading(it: BaseViewModel.ViewState.Loading) {
        postLoading(it.message)
        hideAlert()
    }

    open fun showError(it: BaseViewModel.ViewState.Error) {
        hideLoading()
        postError(it.message)
    }

    private fun postError(e: Throwable) = postError(e.message!!)

    private fun postError(message: String) = showToast(message)

    private fun hideAlert() {
    }

    private fun postLoading(message: String) {
        progressDialog = progressDialog ?: ProgressDialog(this).apply {
            setMessage(message)
            show()
        }
    }

    private fun hideLoading() {
        progressDialog?.cancel()
    }

    override fun onDestroy() {
        for (disposable in disposables) {
            if (!disposable.isDisposed)
                disposable.dispose()
        }

        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            popFragmentSync()
            super.onBackPressed()
        }
    }
}