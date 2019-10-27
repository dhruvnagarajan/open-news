package com.dhruvnagarajan.opennews.view.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dhruvnagarajan.androidplatform.view.BaseActivity
import com.dhruvnagarajan.androidplatform.view.BaseViewModel
import com.dhruvnagarajan.opennews.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * @author Dhruvaraj Nagarajan
 */
class WebViewActivity : BaseActivity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_web)

        val title = intent?.extras?.getString(TITLE) ?: resources.getString(R.string.app_name)
        setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showLoading(BaseViewModel.ViewState.Loading("Fetching newsArticle..."))
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                showSuccess(BaseViewModel.ViewState.Success)
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