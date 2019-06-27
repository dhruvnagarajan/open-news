package com.opensource.news

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Dhruvaraj Nagarajan
 */

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}