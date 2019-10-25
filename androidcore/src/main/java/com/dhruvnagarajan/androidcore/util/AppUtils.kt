package com.dhruvnagarajan.androidcore.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author Dhruvaraj Nagarajan
 */
object AppUtils {

    fun hideKeyboard(context: Context?, v: View?) {
        if (context == null || v == null) return
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}