package com.dhruvnagarajan.androidplatform.util

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.dhruvnagarajan.androidplatform.BuildConfig
import timber.log.Timber

/**
 * @author Dhruvaraj Nagarajan
 */
class InterruptionUtil(private val context: Context) {

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog(title: String?, message: String?) {
        val pd = progressDialog
        try {
            if (pd == null || !pd.isShowing) {
                progressDialog = ProgressDialog(context)
                progressDialog?.setCancelable(false)
                progressDialog?.setCanceledOnTouchOutside(false)
                progressDialog?.setTitle(title)
                progressDialog?.setMessage(message)
                progressDialog?.show()
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG)
                Timber.e(e)
        }
    }

    fun dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog?.dismiss()
                progressDialog = null
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG)
                Timber.e(e)
        }
    }

    fun showAlert(title: String?, t: Throwable) {
        if (BuildConfig.DEBUG)
            Timber.e(t)
        showAlert(title, t.message)
    }

    fun showAlert(title: String?, message: String?) {
        showAlert(title, message, "OK", null)
    }

    fun showAlert(
        title: String?, message: String?,
        positiveButtonText: String, positiveButtonClickListener: DialogInterface.OnClickListener?
    ) {
        showAlert(title, message, positiveButtonText, positiveButtonClickListener, null, null)
    }

    fun showAlert(
        title: String?, message: String?,
        positiveButtonText: String, positiveButtonClickListener: DialogInterface.OnClickListener?,
        negativeButtonText: String?, negativeButtonClickListener: DialogInterface.OnClickListener?
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveButtonClickListener)
                .setNegativeButton(negativeButtonText, negativeButtonClickListener)
                .setCancelable(false)
                .show()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG)
                Timber.e(e)
        }
    }
}