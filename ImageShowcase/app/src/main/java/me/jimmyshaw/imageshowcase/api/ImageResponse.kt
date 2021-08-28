package me.jimmyshaw.imageshowcase.api

import com.google.gson.annotations.SerializedName
import me.jimmyshaw.imageshowcase.models.ShowcaseItem

class ImageResponse {
    @SerializedName("photo")
    lateinit var showcaseItems: List<ShowcaseItem>
}