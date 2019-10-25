package com.opensource.news.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensource.news.R
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.util.ViewModelFactory
import com.opensource.news.util.launchActivity
import com.opensource.news.view.base.BaseActivity
import com.opensource.news.view.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var newsAdapter: NewsAdapter

    override fun getLayout(): Int = R.layout.activity_main

    override fun provideViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

    override fun onCreateView(bundle: Bundle?) {
        newsAdapter = NewsAdapter(this) { clickedArticle ->
            // when an article is clicked, open its details in a WebView
            launchActivity<WebViewActivity> {
                putExtra(WebViewActivity.TITLE, clickedArticle.title)
                putExtra(WebViewActivity.URL, clickedArticle.url)
            }
        }
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = newsAdapter

        viewModel.newsLiveData.observe(this, Observer { newsAdapter.newsList = it.articles })

        viewModel.fetchNews(GetTopHeadlinesUseCase.Params(q = "tesla"))
    }

    override fun showError(message: String?) {
        rv_news.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = message
    }

    override fun hideError() {
        rv_news.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }
}
