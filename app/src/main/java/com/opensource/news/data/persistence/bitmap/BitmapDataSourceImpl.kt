package com.opensource.news.data.persistence.bitmap

import android.graphics.Bitmap
import com.opensource.news.data.persistence.DataSource
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class BitmapDataSourceImpl : DataSource<String, Bitmap> {

    override fun get(key: String): Observable<Bitmap> {
        TODO("not implemented")
    }

    override fun put(key: String, value: Bitmap) {
        TODO("not implemented")
    }

    override fun evict() {
        TODO("not implemented")
    }
}