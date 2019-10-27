package com.dhruvnagarajan.sec.impl

import com.dhruvnagarajan.sec.entity.CipherDataVO
import timber.log.Timber
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class AES(private val passKey: String) {

    @Throws(Exception::class)
    fun encrypt(data: String): CipherDataVO {
        val bytes = data.toByteArray(charset(CHAR_SET_NAME))
        return encryptData(bytes, passKey)
    }

    @Throws(Exception::class)
    private fun encryptData(rawData: ByteArray, passKey: String): CipherDataVO {
        val saltLength = KEY_LENGTH / 8
        val random = SecureRandom()
        val salt = ByteArray(saltLength)
        random.nextBytes(salt)
        val skeySpec = SecretKeySpec(
            getKey(passKey, salt),
            ALGORITHM
        )
        val cipher = Cipher.getInstance(CIPHER_ALGO)
        val iv = ByteArray(cipher.blockSize)
        random.nextBytes(iv)
        val ivParams = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParams)
        val ciphertext = cipher.doFinal(rawData)
        return CipherDataVO(
            passKey,
            salt,
            iv,
            ciphertext
        )
    }

    @Throws(Exception::class)
    private fun getKey(passKey: String, salt: ByteArray): ByteArray {
        val keySpec = PBEKeySpec(
            passKey.toCharArray(), salt,
            ITERATION_COUNT,
            KEY_LENGTH
        )
        val keyFactory = SecretKeyFactory
            .getInstance(SECRET_KEY_ALGO)
        return keyFactory.generateSecret(keySpec).encoded
    }

    fun decrypt(cipherDataVO: CipherDataVO): String? {
        var decryptedData: String? = null
        try {
            val cipher = Cipher.getInstance(CIPHER_ALGO)
            val ivParams = IvParameterSpec(cipherDataVO.iv)
            val skeySpec = SecretKeySpec(
                getKey(cipherDataVO.key, cipherDataVO.salt),
                ALGORITHM
            )
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParams)
            val plaintext = cipher.doFinal(cipherDataVO.encryptedData)
            println("plaintext :" + String(plaintext, StandardCharsets.UTF_8))
            decryptedData = String(plaintext, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            Timber.e(e)
        }

        return decryptedData
    }

    companion object {
        private val ALGORITHM = "AES"
        private val SECRET_KEY_ALGO = "PBKDF2WithHmacSHA1"
        private val CIPHER_ALGO = "AES/CBC/PKCS5Padding"
        private val CHAR_SET_NAME = "UTF-8"
        private val ITERATION_COUNT = 1000
        private val KEY_LENGTH = 256
    }
}