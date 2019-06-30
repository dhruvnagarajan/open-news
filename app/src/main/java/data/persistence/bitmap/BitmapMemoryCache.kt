package data.persistence.bitmap

import android.graphics.Bitmap
import android.util.Log
import data.persistence.Cache

class BitmapMemoryCache : Cache<String, Bitmap?> {

    private val memHash by lazy { HashMap<String, Bitmap>() }

    override fun get(key: String): Bitmap? {
        return memHash[key]
    }

    override fun put(key: String, value: Bitmap?) {
        value ?: return
        Log.d("Mem Cached", key)
        memHash[key] = value
    }
}