package me.jimmyshaw.imageshowcase.models

import com.google.gson.annotations.SerializedName

data class ShowcaseItem(
    var title: String = "",
    var id: String = "",
    @SerializedName("url_s") var url: String = ""
)
