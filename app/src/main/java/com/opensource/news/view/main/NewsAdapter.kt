package com.opensource.news.view.main

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opensource.news.R
import com.opensource.news.domain.model.Article
import com.opensource.news.domain.model.BaseResponse
import com.opensource.news.util.toDateAndTime
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.row_news.view.*
import timber.log.Timber

/**
 * @author Dhruvaraj Nagarajan
 */
class NewsAdapter(
    private val context: Context,
    private val onLoadImg: (url: String) -> Observable<BaseResponse<Bitmap>>,
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
        newsList?.get(position)?.let { article ->

            // if VH was recycled, clear old bitmap request
            if (holder.itemView.tag != null) {
                val rxImage = holder.itemView.tag as RxImage
                if (!rxImage.disposable.isDisposed)
                    rxImage.disposable.dispose()
            }

            // new bitmap request
            val obs = onLoadImg(article.urlToImage ?: "")
            var disposable: Disposable? = null
            obs.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    // bind bitmap response
                    it.data?.let { bitmap -> holder.bindImage(bitmap) }
                }, {
                    Timber.e(it)
                }, {}, { disposable = it })
            holder.itemView.tag = RxImage(obs, disposable!!)

            // bind data as usual
            holder.bind(article)
        }
    }


    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Article) {
            itemView.container.setOnClickListener { onClick(data) }
            itemView.iv_feature_image.setImageDrawable(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    context.resources.getDrawable(R.drawable.ic_twotone_blur_on_24px, null)
                else context.resources.getDrawable(R.drawable.ic_twotone_blur_on_24px)
            )
            itemView.tv_headline.text = data.title
            itemView.tv_gist.text = data.content
            itemView.tv_meta.text = data.publishedAt?.toDateAndTime() +
                    "\n" + data.source?.name
        }

        fun bindImage(bitmap: Bitmap) {
            itemView.iv_feature_image.setImageBitmap(bitmap)
        }
    }

    data class RxImage(
        val observable: Observable<BaseResponse<Bitmap>>,
        val disposable: Disposable
    )
}