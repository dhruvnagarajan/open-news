package data.persistence.bitmap

import android.graphics.Bitmap
import com.opensource.news.domain.model.BaseResponse
import data.persistence.LocalSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class BitmapLocalSourceImpl @Inject constructor(
    private val diskCache: BitmapDiskCache,
    private val memCache: BitmapMemoryCache
) : LocalSource<String, BaseResponse<Bitmap>> {

    /**
     * get from memory if available.
     * get from disk otherwise.
     */
    override fun get(key: String): Observable<BaseResponse<Bitmap>> {
        return Observable.create { emitter ->
            val memoryResponse = memCache.get(key)
            if (memoryResponse != null) {
                // return value from memory
                emitter.onNext(
                    BaseResponse(
                        BaseResponse.Status.SUCCESS,
                        memoryResponse
                    )
                )
            } else {
                // value not found in memory
                // query disk
                val diskResponse = diskCache.get(key)
                if (diskResponse != null) {
                    // return value found on disk
                    memCache.put(key, diskResponse)
                    emitter.onNext(
                        BaseResponse(
                            BaseResponse.Status.SUCCESS,
                            diskResponse
                        )
                    )
                } else {
                    // final case, value not found either in memory or on disk
                    emitter.onNext(
                        BaseResponse(
                            BaseResponse.Status.ERROR,
                            "Resource not found offline."
                        )
                    )
                }
            }
            emitter.onComplete()
        }
    }

    override fun put(key: String, value: BaseResponse<Bitmap>) {
        if (value.data == null) return
        memCache.put(key, value.data)
        diskCache.put(key, value.data)
    }
}