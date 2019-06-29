package com.opensource.news.view.web

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.opensource.news.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * @author Dhruvaraj Nagarajan
 */
class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val title = intent?.extras?.getString(TITLE) ?: resources.getString(R.string.app_name)
        setTitle(title)

        webview.webViewClient = WebViewClient()
        webview.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        val url = intent?.extras?.getString(URL)
        url?.let { webview.loadUrl(it) }
    }

    companion object BundleKeys {
        const val TITLE = "title"
        const val URL = "url"
    }
}