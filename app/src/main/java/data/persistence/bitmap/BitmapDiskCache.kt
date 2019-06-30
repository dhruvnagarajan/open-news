package data.persistence.bitmap

import android.graphics.Bitmap
import data.persistence.Cache

class BitmapDiskCache : Cache<String, Bitmap?> {
    override fun get(key: String): Bitmap? {
        return null
    }

    override fun put(key: String, value: Bitmap?) {
    }
}