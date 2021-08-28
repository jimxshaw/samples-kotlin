package me.jimmyshaw.imageshowcase.api

import com.google.gson.annotations.SerializedName

class FlickrResponse {
    @SerializedName("photos")
    lateinit var images: ImageResponse
}