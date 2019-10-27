package com.opensource.news.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author Dhruvaraj Nagarajan
 */
object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}

fun String.toDateAndTime(): String {
    val dateEndPos = this.indexOf('T')
    val date = this.substring(0, dateEndPos)
    val time = this.substring(dateEndPos + 1, this.length - 1)
    return "$time, $date"
}