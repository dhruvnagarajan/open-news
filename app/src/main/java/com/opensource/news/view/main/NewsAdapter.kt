package com.opensource.news.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.opensource.news.R
import com.opensource.news.domain.model.Article
import com.opensource.news.util.toDateAndTime
import kotlinx.android.synthetic.main.row_news.view.*

/**
 * @author dhruvaraj
 */
class NewsAdapter(
    private val context: Context,
    private val onClick: (article: Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.VH>() {

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
            itemView.container.setOnClickListener { onClick(data) }
            itemView.tv_headline.text = data.title
//            itemView.tv_gist.text = data.content
            itemView.tv_meta.text = getMetaInfo(data)
            Glide.with(context)
                .load(data.urlToImage)
                .placeholder(R.drawable.ic_twotone_blur_on_24px)
                .centerCrop()
                .into(itemView.iv_feature_image)
        }

        private fun getMetaInfo(data: Article): String {
            return "${data.source?.name}, ${data.publishedAt?.toDateAndTime()}"
        }
    }
}