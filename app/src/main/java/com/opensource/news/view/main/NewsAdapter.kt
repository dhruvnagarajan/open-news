package com.opensource.news.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opensource.news.R
import com.opensource.news.domain.model.Article
import kotlinx.android.synthetic.main.row_news.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.VH>() {

    var newsList: List<Article>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.row_news, parent, false))

    override fun getItemCount(): Int = newsList?.size ?: 0

    override fun onBindViewHolder(holder: VH, position: Int) {
        newsList?.get(position)?.let { holder.bind(it) }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Article) {
            itemView.tv_headline.text = data.title
        }
    }
}