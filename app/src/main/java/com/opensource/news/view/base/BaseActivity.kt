package com.opensource.news.view.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.opensource.news.util.showToast
import com.opensource.news.view.progressbottomsheet.ViewStateDialog
import javax.inject.Inject


/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseActivity<T : BaseViewModel> : DaggerXActivity() {

    @Inject
    lateinit var viewStateDialog: ViewStateDialog

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        viewModel = provideViewModel()
        onCreateView()

        // all observers have to use LiveData
        // since LiveData is LifeCycle aware, we can observe it once here
        // otherwise we would've done it only during visibility
        observeViewState()
        onAttachObservers()
    }

    abstract fun getLayout(): Int

    abstract fun provideViewModel(): T

    abstract fun onCreateView()

    private fun observeViewState() {
        viewModel.viewStateLiveData.observe(this, Observer {
            when (it?.viewStateType) {
                BaseViewModel.ViewStateType.NONE -> {
                    hideError()
                    hideLoading()
                    it.message?.let { message -> showToast(message) }
                }
                BaseViewModel.ViewStateType.LOADING -> {
                    hideError()
                    showLoading(it.message)
                }
                BaseViewModel.ViewStateType.ERROR -> {
                    hideLoading()
                    showError(it.message)
                }
            }
        })
    }

    abstract fun onAttachObservers()

    open fun showLoading(message: String? = null) {
        viewStateDialog.showLoading(message)
    }

    open fun hideLoading() {
        viewStateDialog.dismiss()
    }

    open fun showError(message: String?) {
        viewStateDialog.showError(message)
    }

    open fun hideError() {
        viewStateDialog.dismiss()
    }
}