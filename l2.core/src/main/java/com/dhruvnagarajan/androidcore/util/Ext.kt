package com.dhruvnagarajan.androidcore.util

import com.google.gson.Gson

/**
 * @author Dhruvaraj Nagarajan
 */
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)

fun Any.toJson(): String = Gson().toJson(this)