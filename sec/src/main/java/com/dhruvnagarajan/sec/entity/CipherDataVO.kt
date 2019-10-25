package com.dhruvnagarajan.sec.entity

data class CipherDataVO(
    val key: String,
    val salt: ByteArray,
    val iv: ByteArray,
    val encryptedData: ByteArray
)