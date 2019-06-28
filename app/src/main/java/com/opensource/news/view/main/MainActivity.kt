package com.opensource.news.view.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opensource.news.R
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.util.ViewModelFactory
import com.opensource.news.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val newsAdapter by lazy { NewsAdapter() }

    override fun getLayout(): Int = R.layout.activity_main

    override fun provideViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]

    override fun onCreateView() {
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = newsAdapter

        viewModel.newsLiveData.observe(this, Observer { newsAdapter.newsList = it.articles })

        viewModel.fetchNews(GetTopHeadlinesUseCase.Params(country = "in"))
    }
}
