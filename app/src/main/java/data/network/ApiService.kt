package data.network

import com.opensource.news.domain.model.NewsResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @author Dhruvaraj Nagarajan
 */
interface ApiService {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("sources") sources: String? = null,
        @Query("q") q: String? = null,
        @Query("language") language: String? = null,
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String
    ): Observable<Response<NewsResponse>>

    @GET
    fun getImageData(@Url url: String): Call<ResponseBody>
}