package com.opensource.news.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.opensource.news.R
import com.opensource.news.domain.entity.Article
import com.opensource.news.domain.entity.NewsRequest
import com.opensource.news.util.toDateAndTime
import kotlinx.android.synthetic.main.row_news.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsAdapter(
    private val context: Context,
    private val onClick: (item: Item) -> Unit
) : RecyclerView.Adapter<NewsAdapter.VH>() {

    var newsList: List<Item>? = null
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

        fun bind(data: Item) {
            itemView.container.setOnClickListener { onClick(data) }

            Glide.with(context)
                .load(data.article.urlToImage)
                .placeholder(R.drawable.ic_twotone_blur_on_24px)
                .centerCrop()
                .into(itemView.iv_feature_image)

            itemView.tv_source.text = data.request.q
            itemView.tv_time.text = data.article.publishedAt?.toDateAndTime()
            itemView.tv_author.text = data.article.author
            itemView.tv_headline.text = data.article.title
            itemView.tv_gist.text = data.article.content
        }
    }

    data class Item(
        val request: NewsRequest,
        val article: Article
    )
}