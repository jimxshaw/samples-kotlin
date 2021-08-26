package me.jimmyshaw.imageshowcase.api

import android.content.res.Resources
import me.jimmyshaw.imageshowcase.R
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {

    @GET
    fun fetchImages(
        @Url url: String = Resources.getSystem().getString(R.string.flickr_get_images_url)
    ): Call<String>
}