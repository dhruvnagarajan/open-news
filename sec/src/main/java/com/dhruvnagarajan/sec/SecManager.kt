package com.dhruvnagarajan.sec

import android.util.Base64
import com.dhruvnagarajan.sec.entity.CipherDataVO
import com.dhruvnagarajan.sec.entity.EncryptedDataVO
import com.dhruvnagarajan.sec.impl.AES
import com.dhruvnagarajan.sec.impl.RSA
import com.google.gson.Gson
import io.reactivex.Observable
import timber.log.Timber
import java.util.*

/**
 * @param userName required to generate a key using which the server could verify the end user
 */
class SecManager(
    private val aes: AES,
    private val rsa: RSA,
    private val userName: String
) {

    private val gson = Gson()

    fun getEncryptedRequest(request: Any): Observable<Any?> =
        Observable.create { emitter ->
            if (!BuildConfig.ENC_ENABLED) {
                emitter.onNext(request)
                emitter.onComplete()
                return@create
            }

            val data = gson.toJson(request)

            if (BuildConfig.DEBUG)
                Timber.i("Original Request: %s", data)

            val aesData = aes.encrypt(data)
            val rsaData = rsa.encrypt(getFormattedAESKey(aesData))

            val aesStr = Base64.encodeToString(aesData.encryptedData, Base64.DEFAULT)
            val requestData = EncryptedDataVO(aesStr, rsaData)

            val requestMap = HashMap<String, String>()

            requestMap["data"] = requestData.data
            requestMap["key"] = requestData.key

            emitter.onNext(requestMap)
            emitter.onComplete()
        }

    private fun getFormattedAESKey(edCipherData: CipherDataVO): String {
        val builder = StringBuilder()
        builder.append(edCipherData.key)
        builder.append(userName)
        builder.append(Base64.encodeToString(edCipherData.salt, Base64.DEFAULT))
        builder.append(userName)
        builder.append(Base64.encodeToString(edCipherData.iv, Base64.DEFAULT))
        return builder.toString()
    }
}
