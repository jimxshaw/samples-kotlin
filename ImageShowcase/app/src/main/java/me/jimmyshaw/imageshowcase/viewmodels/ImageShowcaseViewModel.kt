package me.jimmyshaw.imageshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.jimmyshaw.imageshowcase.api.FlickrFetchr
import me.jimmyshaw.imageshowcase.models.ShowcaseItem

class ImageShowcaseViewModel : ViewModel() {
    val showcaseItemLiveData: LiveData<List<ShowcaseItem>>

    init {
        showcaseItemLiveData = FlickrFetchr().fetchImages()
    }
}