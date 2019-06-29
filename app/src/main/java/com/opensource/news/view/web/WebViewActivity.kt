package com.opensource.news.view.web

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.opensource.news.R
import com.opensource.news.view.base.DaggerXActivity
import com.opensource.news.view.progressbottomsheet.ViewStatePrompt
import kotlinx.android.synthetic.main.activity_web.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class WebViewActivity : DaggerXActivity() {

    @Inject
    lateinit var viewStatePrompt: ViewStatePrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val title = intent?.extras?.getString(TITLE) ?: resources.getString(R.string.app_name)
        setTitle(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showLoading("Loading website...")
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                hideLoading()
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

    private fun showLoading(message: String? = null) {
        viewStatePrompt.showLoading(message)
    }

    private fun hideLoading() {
        viewStatePrompt.dismiss()
    }


    companion object BundleKeys {
        const val TITLE = "title"
        const val URL = "url"
    }
}