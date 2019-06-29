package data.repository

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import data.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class ImageRepository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService

    private val bitMapHash by lazy { HashMap<String, Bitmap>() }

    @SuppressLint("CheckResult")
    fun getImage(url: String): Observable<Bitmap> {
        val memoryResponse = getFromMemory(url)
        return if (memoryResponse != null) {
            Observable.create {
                it.onNext(memoryResponse)
                it.onComplete()
            }
        } else {
            val diskResponse = getImageFromDisk(url)
            return if (diskResponse != null) {
                Observable.create {
                    it.onNext(diskResponse)
                    it.onComplete()
                }
            } else {
                getImageFromNetwork(url)
            }
        }
    }

    private fun getFromMemory(key: String): Bitmap? {
        return bitMapHash[key]
    }

    private fun cacheInMemory(key: String, value: Bitmap) {
        bitMapHash[key] = value
    }

    private fun getImageFromDisk(url: String): Bitmap? {
        return null
    }

    private fun cacheInDisk(key: String, value: Bitmap) {}

    private fun getImageFromNetwork(url: String): Observable<Bitmap> {
        return Observable.create {
            val response = apiService.getImageData(url).execute()
            val bytes = response.body()?.bytes()
            bytes ?: it.onComplete()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes!!.size)
            it.onNext(bitmap)
            it.onComplete()
        }
    }
}