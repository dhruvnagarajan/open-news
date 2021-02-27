package com.opensource.news.view.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProviders
import com.opensource.news.R
import com.opensource.news.util.ViewModelFactory
import com.opensource.news.view.base.BaseActivity
import com.opensource.news.view.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_web.*
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class WebViewActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BaseViewModel

    override fun getLayout(): Int = R.layout.activity_web

    override fun provideViewModel(): BaseViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[BaseViewModel::class.java]
        return viewModel
    }

    override fun onCreateView(bundle: Bundle?) {
        val title = intent?.extras?.getString(TITLE) ?: resources.getString(R.string.app_name)
        setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                viewModel.viewStateLiveData.postValue(
                    BaseViewModel.ViewState(
                        BaseViewModel.ViewStateType.LOADING,
                        "Fetching source..."
                    )
                )
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                viewModel.viewStateLiveData.postValue(
                    BaseViewModel.ViewState(
                        BaseViewModel.ViewStateType.NONE
                    )
                )
            }
        }

        webview.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        val url = intent?.extras?.getString(URL)
        url?.let { webview.loadUrl(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object BundleKeys {
        const val TITLE = "title"
        const val URL = "url"
    }
}