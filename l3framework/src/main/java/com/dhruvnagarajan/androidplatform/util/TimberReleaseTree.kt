package com.dhruvnagarajan.androidplatform.util

import timber.log.Timber

/**
 * @author Dhruvaraj Nagarajan
 */
object TimberReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    }
}