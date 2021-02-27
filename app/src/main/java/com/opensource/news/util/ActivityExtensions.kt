package com.opensource.news.util

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author dhruvaraj
 */

fun AppCompatActivity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

inline fun <reified A> AppCompatActivity.launchActivity(init: Intent.() -> Unit = {}) {
    val intent = Intent(this, A::class.java)
    intent.init()
    startActivity(intent)
}