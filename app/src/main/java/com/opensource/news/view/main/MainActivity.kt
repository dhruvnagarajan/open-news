package com.opensource.news.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.opensource.news.R
import com.opensource.news.util.ViewModelFactory
import com.opensource.news.util.launchActivity
import com.opensource.news.view.base.BaseActivity
import com.opensource.news.view.configure.ConfigureTopicBottomSheet
import com.opensource.news.view.web.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var viewModel: MainViewModel

    override fun getLayout(): Int = R.layout.activity_main

    override fun provideViewModel(): MainViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        return viewModel
    }

    override fun onCreateView(bundle: Bundle?) {
        setSupportActionBar(toolbar)

        tv_title.text = "Open News"

        newsAdapter = NewsAdapter(this) { clickedArticle ->
            // when an article is clicked, open its details in a WebView
            launchActivity<WebViewActivity> {
                putExtra(WebViewActivity.TITLE, clickedArticle.title)
                putExtra(WebViewActivity.URL, clickedArticle.url)
            }
        }
        rv_news.layoutManager = GridLayoutManager(this, 2)
        rv_news.adapter = newsAdapter

        iv_settings.setOnClickListener {
            val bottomSheet = ConfigureTopicBottomSheet().apply {
                onConfigSaved = {
                    viewModel.fetchNews()
                }
            }
            bottomSheet.show(supportFragmentManager, ConfigureTopicBottomSheet::class.java.name)
        }

        viewModel.newsLiveData.observe(this, Observer { newsAdapter.newsList = it.articles })

        viewModel.fetchNews()
    }

    override fun showError(message: String?) {
        rv_news.visibility = View.GONE
        tv_news_msg.visibility = View.VISIBLE
        tv_news_msg.text = message
    }

    override fun hideError() {
        rv_news.visibility = View.VISIBLE
        tv_news_msg.visibility = View.GONE
    }
}
