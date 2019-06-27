package com.opensource.news.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensource.news.BaseActivity
import com.opensource.news.R
import com.opensource.news.ViewModelFactory
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity<MainViewModel>() {

    private val newsAdapter by lazy { NewsAdapter() }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayout(): Int = R.layout.activity_main

    override fun provideViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

    override fun onCreateView() {
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = newsAdapter

        viewModel.fetchNews(GetTopHeadlinesUseCase.Params(country = "in"))
    }

    override fun onAttachObservers() {
        viewModel.newsLiveData.observe(this, Observer { newsAdapter.newsList = it.articles })
    }
}
