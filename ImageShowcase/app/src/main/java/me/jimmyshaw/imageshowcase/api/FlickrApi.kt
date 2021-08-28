package me.jimmyshaw.imageshowcase.api

import me.jimmyshaw.imageshowcase.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList&format=json&nojsoncallback=1&extras=url_s")
    fun fetchImages(
        // https://guides.codepath.com/android/Storing-Secret-Keys-in-Android
        // Note that the BuildConfig approach does not work in the @GET annotation.
        @Query("api_key") apiKey: String = BuildConfig.FLICKR_API_KEY
    ): Call<FlickrResponse>


}