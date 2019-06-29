package data.persistence.bitmap

import android.graphics.Bitmap
import androidx.collection.LruCache
import data.persistence.Cache
import timber.log.Timber

class BitmapMemoryCache : Cache<String, Bitmap?> {

    private val memHash by lazy {
        object : LruCache<String, Bitmap>((Runtime.getRuntime().maxMemory() / 1024 / 8).toInt()) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }

    override fun get(key: String): Bitmap? {
        return memHash[key]
    }

    override fun put(key: String, value: Bitmap?) {
        value ?: return
        Timber.d("Mem Cached : $key")
        memHash.put(key, value)
    }
}