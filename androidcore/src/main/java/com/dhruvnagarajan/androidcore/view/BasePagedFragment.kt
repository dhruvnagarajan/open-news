package com.dhruvnagarajan.androidcore.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dhruvnagarajan.androidcore.util.ext.attachObserver
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BasePagedFragment<T> : BaseSearchFragment<T>() {

    protected var currentPage: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadPage(currentPage + 1)
                }
            }
        })

        loadPage(currentPage + 1)
    }

    protected open fun loadPage(page: Int) {
        getResultForPage(page).attachObserver(getBaseObserver({
            notifyAdapter(it)
            currentPage++
        }, {
            postError(it)
        }))
    }

    protected abstract fun getResultForPage(position: Int): Observable<List<T>>
}