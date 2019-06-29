package data.persistence.bitmap

import android.graphics.Bitmap
import data.persistence.LocalSource
import io.reactivex.Observable

/**
 * @author Dhruvaraj Nagarajan
 */
class BitmapLocalSourceImpl : LocalSource<String, Bitmap> {

    override fun get(key: String): Observable<Bitmap> {
        TODO("not implemented")
    }

    override fun put(key: String, value: Bitmap) {
        TODO("not implemented")
    }
}