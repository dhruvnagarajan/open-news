package com.opensource.news.view.main

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.opensource.news.R
import com.opensource.news.domain.model.Article
import com.opensource.news.util.toDateAndTime
import kotlinx.android.synthetic.main.row_news.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsAdapter(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val onLoadImg: (url: String) -> MutableLiveData<Bitmap>,
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

            itemView.iv_feature_image.setImageDrawable(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    context.resources.getDrawable(R.drawable.ic_twotone_blur_on_24px, null)
                else context.resources.getDrawable(R.drawable.ic_twotone_blur_on_24px)
            )

            if (data.urlToImage?.isNotEmpty() != false) {
                val bitmapLiveData: MutableLiveData<Bitmap>
                if (itemView.iv_feature_image.tag == null) {
                    bitmapLiveData = onLoadImg(data.urlToImage!!)
                    itemView.iv_feature_image.tag = bitmapLiveData
                } else bitmapLiveData = itemView.iv_feature_image.tag as MutableLiveData<Bitmap>
                bitmapLiveData.observe(lifecycleOwner, Observer { itemView.iv_feature_image.setImageBitmap(it) })
            }

            itemView.tv_headline.text = data.title

            itemView.tv_gist.text = data.content

            itemView.tv_meta.text = getMetaInfo(data)
        }

        private fun getMetaInfo(data: Article): String {
            return data.publishedAt?.toDateAndTime() + "\n" + data.source?.name
        }
    }
}