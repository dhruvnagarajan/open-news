package com.dhruvnagarajan.sec.impl


import android.content.Context
import android.util.Base64
import com.dhruvnagarajan.sec.Encrypt
import org.apache.commons.io.IOUtils
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.GeneralSecurityException
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class RSA(
    private val context: Context,
    private val publicKeyFileName: String
) : Encrypt {

    @Throws(IOException::class, GeneralSecurityException::class)
    override fun encrypt(data: String): String {
        val am = context.assets
        val `in` = am.open(publicKeyFileName)

        val x509EncodedKeySpec =
            X509EncodedKeySpec(Base64.decode(IOUtils.toByteArray(`in`), Base64.DEFAULT))

        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec)
        )

        return Base64.encodeToString(
            cipher.doFinal(data.toByteArray(StandardCharsets.UTF_8)),
            Base64.DEFAULT
        )
    }
}