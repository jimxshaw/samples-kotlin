package me.jimmyshaw.imageshowcase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.imageshowcase.R
import me.jimmyshaw.imageshowcase.api.FlickrFetchr
import me.jimmyshaw.imageshowcase.models.ShowcaseItem

private const val TAG = "ImageShowcaseFragment"

class ImageShowcaseFragment : Fragment() {

    private lateinit var imageRecyclerView: RecyclerView

    companion object {
        fun newInstance() = ImageShowcaseFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val flickrLiveData: LiveData<List<ShowcaseItem>> = FlickrFetchr().fetchImages()

        flickrLiveData.observe(this, Observer { showcaseItem ->
            Log.d(TAG, "Response received: $showcaseItem")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_showcase, container, false)

        imageRecyclerView = view.findViewById(R.id.recycler_view_image)
        imageRecyclerView.layoutManager = GridLayoutManager(context, 3)

        return view
    }
}