package com.dhruvnagarajan.opennews.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhruvnagarajan.opennews.R
import com.dhruvnagarajan.opennews.domain.entity.NewsArticle
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.util.toDateAndTime
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
                .load(data.newsArticle.urlToImage)
                .placeholder(R.drawable.ic_twotone_blur_on_24px)
                .centerCrop()
                .into(itemView.iv_feature_image)

            itemView.tv_source.text = data.profile.q
            itemView.tv_time.text = data.newsArticle.publishedAt?.toDateAndTime()
            itemView.tv_author.text = data.newsArticle.author
            itemView.tv_headline.text = data.newsArticle.title
            itemView.tv_gist.text = data.newsArticle.content
        }
    }

    data class Item(
        val profile: NewsProfile,
        val newsArticle: NewsArticle
    )
}