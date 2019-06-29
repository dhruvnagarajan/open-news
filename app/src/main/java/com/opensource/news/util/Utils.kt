package com.opensource.news.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
fun String.toDateAndTime(): String {
    val dateEndPos = this.indexOf('T')
    val date = this.substring(0, dateEndPos)
    val time = this.substring(dateEndPos, this.length - 1)
    return date
}

class NetworkUtils @Inject constructor(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}