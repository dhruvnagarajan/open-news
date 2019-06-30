package data.repository

import android.graphics.Bitmap
import com.opensource.news.domain.model.BaseResponse
import data.network.bitmap.BitmapNetworkSourceImpl
import data.persistence.bitmap.BitmapLocalSourceImpl
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class ImageRepository @Inject constructor(
    private val bitmapLocalSourceImpl: BitmapLocalSourceImpl,
    private val bitmapNetworkSourceImpl: BitmapNetworkSourceImpl
) :
    OfflineFirstRepository<String, BaseResponse<Bitmap>>(
        bitmapLocalSourceImpl, bitmapNetworkSourceImpl
    ) {

//    @SuppressLint("CheckResult")
//    fun getImage(url: String): Observable<Bitmap> {
//        val memoryResponse = getFromMemory(url)
//        return if (memoryResponse != null) {
//            Observable.create {
//                it.onNext(memoryResponse)
//                it.onComplete()
//            }
//        } else {
//            val diskResponse = getImageFromDisk(url)
//            return if (diskResponse != null) {
//                Observable.create {
//                    it.onNext(diskResponse)
//                    it.onComplete()
//                }
//            } else {
//                getImageFromNetwork(url)
//            }
//        }
//    }

    fun getImage(url: String): Observable<BaseResponse<Bitmap>> {
        return getFromAnySource(url)
    }
}