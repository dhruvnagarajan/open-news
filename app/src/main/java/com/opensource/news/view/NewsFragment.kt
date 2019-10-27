package com.opensource.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhruvnagarajan.androidplatform.util.ViewModelFactory
import com.dhruvnagarajan.androidplatform.util.ext.attachObserver
import com.dhruvnagarajan.androidplatform.util.ext.getActivityViewModel
import com.dhruvnagarajan.androidplatform.view.BaseFragment
import com.dhruvnagarajan.androidplatform.util.ext.startActivity
import com.opensource.news.R
import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.view.main.MainViewModel
import com.opensource.news.view.main.NewsAdapter
import com.opensource.news.view.web.WebViewActivity
import kotlinx.android.synthetic.main.fragment_news.view.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var newsAdapter: NewsAdapter

    lateinit var profile: NewsProfile

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getActivityViewModel(viewModelFactory)

        val context = context!!

        newsAdapter = NewsAdapter(context) { clickedArticle ->
            // open newsArticle's details in a WebView
            startActivity<WebViewActivity>(context) {
                putExtra(WebViewActivity.TITLE, clickedArticle.newsArticle.title)
                putExtra(WebViewActivity.URL, clickedArticle.newsArticle.url)
            }
        }
        view.rv_news.layoutManager = LinearLayoutManager(context)
        view.rv_news.adapter = newsAdapter

        viewModel.fetchNews(profile).attachObserver(getBaseObserver({
            newsAdapter.newsList = it
        }))
    }

    override fun postSuccess() {
        val view = view!!
        view.rv_news.visibility = View.VISIBLE
        view.tv_error.visibility = View.GONE
    }

    override fun postError(message: String) {
        val view = view!!
        view.rv_news.visibility = View.GONE
        view.tv_error.visibility = View.VISIBLE
        view.tv_error.text = message
    }
}