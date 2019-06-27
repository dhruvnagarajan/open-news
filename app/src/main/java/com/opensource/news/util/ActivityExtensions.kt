package com.opensource.news.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Dhruvaraj Nagarajan
 */

fun AppCompatActivity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}