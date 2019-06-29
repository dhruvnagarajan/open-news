package data.network.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.opensource.news.domain.model.BaseResponse
import data.network.ApiService
import data.network.NetworkSource
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class BitmapNetworkSourceImpl @Inject constructor() : NetworkSource<String, BaseResponse<Bitmap>> {

    @Inject
    lateinit var apiService: ApiService

    override fun get(request: String): Observable<BaseResponse<Bitmap>> {
        return Observable.create {
            try {
                val response = apiService.getImageData(request).execute()
                val bytes = response.body()?.bytes()
                bytes ?: it.onComplete()
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes!!.size)
                it.onNext(BaseResponse(BaseResponse.Status.SUCCESS, bitmap))
                it.onComplete()
            } catch (e: Exception) {
                if (!it.isDisposed)
                    it.onError(e)
            }
        }
    }
}