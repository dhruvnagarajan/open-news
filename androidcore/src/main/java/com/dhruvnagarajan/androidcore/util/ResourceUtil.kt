package com.dhruvnagarajan.androidcore.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.dhruvnagarajan.androidcore.BuildConfig
import org.json.JSONArray
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets


/**
 * @author Dhruvaraj Nagarajan
 */
object ResourceUtil {

    fun getColor(context: Context, @ColorRes colorId: Int): Int {
        return context.resources.getColor(colorId)
    }

    fun getDrawable(context: Context, @DrawableRes drawableId: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableId)
    }

    fun getDrawableId(context: Context, drawableName: String): Int {
        return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
    }

    fun getJsonString(context: Context, fileName: String): String? {
        try {
            val inputStream = context.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }
            inputStream.close()
            bufferedReader.close()
            return stringBuilder.toString()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG)
                Timber.e(e)
        }
        return null
    }

    fun getJsonArray(context: Context, filename: String): JSONArray? {
        try {
            val inputStream = context.assets.open(filename)
            val buffer_size = inputStream.available()
            val buffer = ByteArray(buffer_size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, StandardCharsets.UTF_8)
            return JSONArray(jsonString)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG)
                Timber.e(e)
        }
        return null
    }
}