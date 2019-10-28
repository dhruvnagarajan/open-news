package com.dhruvnagarajan.androidcore.persistence

import android.content.Context
import com.dhruvnagarajan.androidcore.util.fromJson
import com.dhruvnagarajan.androidcore.util.toJson

/**
 * @author Dhruvaraj Nagarajan
 */
class DiskCache(private val context: Context) : Cache {

    override fun get(key: String): String? {
        return getSharedPreference().run {
            if (contains(key)) {
                getString(key, null)!!.fromJson<String>()
            }
            null
        }
    }

    override fun <V : Any> put(key: String, value: V) {
        getSharedPreference().edit().putString(key, value.toJson()).apply()
    }

    override fun evict() {
        getSharedPreference().edit().clear().apply()
    }

    private fun getSharedPreference() = context.getSharedPreferences("root", Context.MODE_PRIVATE)
}