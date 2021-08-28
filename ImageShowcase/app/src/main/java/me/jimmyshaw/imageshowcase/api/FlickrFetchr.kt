package me.jimmyshaw.imageshowcase.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.jimmyshaw.imageshowcase.models.ShowcaseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi: FlickrApi

    init {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun fetchImages(): LiveData<List<ShowcaseItem>> {
        val responseLiveData: MutableLiveData<List<ShowcaseItem>> = MutableLiveData()
        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchImages()

        flickrRequest.enqueue(object : Callback<FlickrResponse> {
            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                Log.d(TAG, "Response received")

                val flickrResponse: FlickrResponse? = response.body()
                val photoResponse: ImageResponse? = flickrResponse?.images

                var showcaseItems: List<ShowcaseItem> = photoResponse?.showcaseItems
                    ?: mutableListOf()

                showcaseItems = showcaseItems.filterNot {
                    it.url.isBlank()
                }

                responseLiveData.value = showcaseItems
            }
        })
        return responseLiveData
    }
}