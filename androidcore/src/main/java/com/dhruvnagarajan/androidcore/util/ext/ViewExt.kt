package com.dhruvnagarajan.androidcore.util.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import timber.log.Timber

/**
 * @author Dhruvaraj Nagarajan
 */

// visibility modifiers
fun View.toggleVisibility(showView: Boolean) {
    if (showView) show()
    else hide()
}

fun View.show() {
    if (this.visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun View.hide() {
    if (this.visibility != View.GONE)
        this.visibility = View.GONE
}

// prompts
fun AppCompatActivity.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.showAlert(
    title: String?,
    message: String?,
    positiveButtonText: String = "OK",
    positiveClick: () -> Unit = {},
    negativeButtonText: String? = null,
    negativeClick: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { _, _ -> positiveClick() }
        .setNegativeButton(negativeButtonText) { _, _ -> negativeClick() }
        .setCancelable(false)
        .show()
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(this.window, this)
}

fun Fragment.hideKeyboard() {
    if (this.activity?.window != null && context != null)
        hideKeyboard(this.activity!!.window, context!!)
}

fun View.hideKeyboard() {
    try {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    } catch (e: Exception) {
        Timber.i(e)
    }
}

private fun hideKeyboard(window: Window, context: Context) {
    try {
        val focusView = window.currentFocus
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        focusView?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    } catch (e: Exception) {
        Timber.i(e)
    }
}