package me.jimmyshaw.imageshowcase.utilities

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException
import java.io.InputStream
import java.util.*


class Util {
    companion object {
        @Throws(IOException::class)
        fun getProperty(context: Context, propertyFileName: String, key: String?): String? {
            val properties = Properties()
            val assetManager: AssetManager = context.assets
            val inputStream: InputStream = assetManager.open(propertyFileName)

            properties.load(inputStream)

            return properties.getProperty(key)
        }
    }
}