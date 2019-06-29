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
    bitmapLocalSourceImpl: BitmapLocalSourceImpl,
    bitmapNetworkSourceImpl: BitmapNetworkSourceImpl
) : OfflineFirstRepository<String, BaseResponse<Bitmap>>(
    bitmapLocalSourceImpl, bitmapNetworkSourceImpl
) {

    fun getImage(url: String): Observable<BaseResponse<Bitmap>> = getFromAnySource(url)
}