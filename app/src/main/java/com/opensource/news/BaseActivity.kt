package com.opensource.news

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity


/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseActivity<T : BaseViewModel> : DaggerAppCompatActivity() {

    lateinit var viewModel: T

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        viewModel = provideViewModel()
        onCreateView()
    }

    override fun onStart() {
        super.onStart()
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
                    showLoading()
                    it.message?.let { message -> showToast(message) }
                }
                BaseViewModel.ViewStateType.ERROR -> {
                    hideLoading()
                    showError(it.message)
                }
            }
        })
    }

    abstract fun onAttachObservers()

    open fun showError(message: String?) {
        message?.let { showToast(it) }
    }

    open fun hideError() {}

    open fun showLoading(message: String? = null) {
        if (alertDialog?.isShowing == false)
            alertDialog = AlertDialog.Builder(this)
                .setMessage(message ?: "Loading...")
                .show()
    }

    open fun hideLoading() {
        if (alertDialog?.isShowing == true)
            alertDialog?.dismiss()
    }
}