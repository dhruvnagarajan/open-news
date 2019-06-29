package com.opensource.news.util

/**
 * @author Dhruvaraj Nagarajan
 */
fun String.toDateAndTime(): String {
    val dateEndPos = this.indexOf('T')
    val date = this.substring(0, dateEndPos)
    val time = this.substring(dateEndPos, this.length - 1)
    return date
}