package com.opensource.news.util

import android.app.Application
import android.content.Context
import javax.inject.Inject


class DiskCache @Inject constructor(
    private val application: Application
) {

    fun get(key: String): String? {
        return getSharedPreference().run {
            if (contains(key)) {
                return getString(key, null)
            }
            null
        }
    }

    fun <V : Any> put(key: String, value: V?) {
        getSharedPreference().edit().putString(key, value?.toJson()).apply()
    }

    fun evict() {
        getSharedPreference().edit().clear().apply()
    }

    private fun getSharedPreference() =
        application.getSharedPreferences("root", Context.MODE_PRIVATE)
}