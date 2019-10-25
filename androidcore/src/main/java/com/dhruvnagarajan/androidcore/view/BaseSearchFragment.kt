package com.dhruvnagarajan.androidcore.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhruvnagarajan.androidcore.R
import com.dhruvnagarajan.androidcore.util.ext.attachObserver
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.layout_search.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseSearchFragment<T> : BaseFragment() {

    protected var lastTextChangeTime = System.currentTimeMillis()
    protected var recentQuery: String = ""

    protected var textChangeTimeDelta = 1000L

    lateinit var baseRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTextChangeTime > textChangeTimeDelta) {
                    lastTextChangeTime = currentTime
                    recentQuery = s.toString()

                    getSearchResult(recentQuery).attachObserver(getBaseObserver({
                        notifyAdapter(it)
                    }))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        baseRVAdapter = getAdapter()
        view.rv.layoutManager = LinearLayoutManager(context)
        view.rv.adapter = baseRVAdapter
    }

    abstract fun getAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>

    abstract fun notifyAdapter(result: List<T>)

    abstract fun getSearchResult(query: String): Observable<List<T>>

    abstract fun getSearchResultClickListener(): (t: T) -> Unit
}