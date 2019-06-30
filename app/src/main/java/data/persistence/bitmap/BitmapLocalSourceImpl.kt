package data.persistence.bitmap

import android.graphics.Bitmap
import android.util.Log
import com.opensource.news.domain.model.BaseResponse
import data.persistence.LocalSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class BitmapLocalSourceImpl @Inject constructor() : LocalSource<String, BaseResponse<Bitmap>> {

    private val bitMapHash by lazy { HashMap<String, Bitmap>() }

    override fun get(key: String): Observable<BaseResponse<Bitmap>> {
        val memoryResponse = getFromMemory(key)
        return if (memoryResponse != null) {
            Observable.create {
                it.onNext(
                    BaseResponse(
                        BaseResponse.Status.SUCCESS,
                        memoryResponse
                    )
                )
                it.onComplete()
            }
        } else {
            val diskResponse = getImageFromDisk(key)
            return Observable.create {
                if (diskResponse != null) {
                    it.onNext(
                        BaseResponse(
                            BaseResponse.Status.SUCCESS,
                            diskResponse
                        )
                    )
                } else {
                    it.onNext(
                        BaseResponse(
                            BaseResponse.Status.ERROR,
                            "Resource not found offline."
                        )
                    )
                }

                it.onComplete()
            }
        }
    }

    override fun put(key: String, value: BaseResponse<Bitmap>) {
        Log.e("Cached", key)
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
}