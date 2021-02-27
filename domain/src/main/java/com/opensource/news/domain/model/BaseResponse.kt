package com.opensource.news.domain.model

/**
 * @author dhruvaraj
 */
data class BaseResponse<T>(
    val status: Status,
    val message: String,
    var data: T? = null
) {
    constructor(
        status: Status,
        data: T? = null,
        message: String = ""
    ) : this(status, message, data)

    enum class Status {
        SUCCESS,
        ERROR
    }
}