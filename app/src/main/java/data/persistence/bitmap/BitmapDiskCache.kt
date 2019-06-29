package data.persistence.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.jakewharton.disklrucache.DiskLruCache
import com.opensource.news.BuildConfig
import data.persistence.Cache
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.withLock

@Singleton
class BitmapDiskCache @Inject constructor(private val context: Context) : Cache<String, Bitmap?> {

    private val cacheSize = (1024 * 1024 * 40).toLong()
    private val cacheSubDir = "bitmap_cache"
    private var diskLruCache: DiskLruCache? = null
    private val diskCacheLock = ReentrantLock()
    private var diskCacheStarting = true
    private val diskCacheLockCondition: Condition = diskCacheLock.newCondition()

    init {
        diskCacheLock.withLock {
            val cacheDir = getDiskCacheDir(context)
            diskLruCache = DiskLruCache.open(cacheDir, BuildConfig.VERSION_CODE, 1, cacheSize)
            diskCacheStarting = false
            diskCacheLockCondition.signalAll()
        }
    }

    @Throws(InterruptedException::class, IOException::class, NoSuchAlgorithmException::class)
    override operator fun get(key: String): Bitmap? {
        var key = key
        key = getValidKey(key)
        synchronized(diskCacheLock) {
            while (diskCacheStarting) {
                try {
                    diskCacheLockCondition.await()
                } catch (e: InterruptedException) {
                    close()
                    throw e
                }

            }
            return if (diskLruCache != null) {
                var result: Bitmap? = null
                try {
                    diskLruCache!!.get(key).use { snapshot ->
                        if (snapshot != null)
                            result = BitmapFactory.decodeStream(snapshot.getInputStream(0))
                    }
                } catch (e: Exception) {
                    close()
                    throw e
                }

                result
            } else null
        }
    }

    @Throws(IOException::class, NoSuchAlgorithmException::class)
    override fun put(key: String, bitmap: Bitmap?) {
        bitmap ?: return
        var key = key
        key = getValidKey(key)
        synchronized(diskCacheLock) {
            var editor: DiskLruCache.Editor? = null
            try {
                if (diskLruCache != null && diskLruCache!!.get(key) == null) {
                    editor = diskLruCache!!.edit(key)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, editor!!.newOutputStream(0))
                    editor.commit()
                }
            } catch (e: IOException) {
                if (editor != null) {
                    try {
                        editor.abort()
                    } catch (e2: IOException) {
                        close()
                        throw e2
                    }

                }
                close()
                throw e
            }

        }
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun getValidKey(key: String): String {
        val md = MessageDigest.getInstance("md5")
        var hash = md.digest(key.toByteArray()).toString()
        if (hash.length > 64)
            hash = hash.substring(0, 64)
        hash = hash.toLowerCase()
        return hash.replace("[^a-z0-9_-]".toRegex(), "_")
    }

    private fun getDiskCacheDir(context: Context): File {
        val cachePath =
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable())
                context.externalCacheDir!!.path
            else
                context.cacheDir.path
        return File(cachePath + File.separator + cacheSubDir)
    }

    @Throws(IOException::class)
    fun close() {
        synchronized(diskCacheLock) {
            try {
                if (!diskLruCache!!.isClosed) {
                    diskLruCache!!.close()
                }

                diskLruCache!!.delete()
            } catch (e: IOException) {
                throw e
            }

        }
    }
}