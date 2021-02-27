package com.opensource.news.view.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.opensource.news.util.showToast
import com.opensource.news.view.progressbottomsheet.ViewStatePrompt
import javax.inject.Inject


/**
 * @author dhruvaraj
 */
abstract class BaseActivity : DaggerXActivity() {

    @Inject
    lateinit var viewStatePrompt: ViewStatePrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        observeViewState()
        onCreateView(savedInstanceState)
    }

    abstract fun getLayout(): Int

    abstract fun provideViewModel(): BaseViewModel

    abstract fun onCreateView(bundle: Bundle?)

    private fun observeViewState() {
        provideViewModel().viewStateLiveData.observe(this, Observer {
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

    open fun showLoading(message: String? = null) {
        viewStatePrompt.showLoading(message)
    }

    open fun hideLoading() {
        viewStatePrompt.dismiss()
    }

    open fun showError(message: String?) {
        viewStatePrompt.showError(message)
    }

    open fun hideError() {
        viewStatePrompt.dismiss()
    }
}