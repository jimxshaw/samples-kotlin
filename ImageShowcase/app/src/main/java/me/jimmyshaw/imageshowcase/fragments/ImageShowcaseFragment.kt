package me.jimmyshaw.imageshowcase.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.imageshowcase.R
import me.jimmyshaw.imageshowcase.api.FlickrFetchr
import me.jimmyshaw.imageshowcase.models.ShowcaseItem
import me.jimmyshaw.imageshowcase.viewmodels.ImageShowcaseViewModel

private const val TAG = "ImageShowcaseFragment"

class ImageShowcaseFragment : Fragment() {

    private lateinit var imageShowcaseViewModel: ImageShowcaseViewModel
    private lateinit var imageRecyclerView: RecyclerView

    companion object {
        fun newInstance() = ImageShowcaseFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageShowcaseViewModel = ViewModelProvider(this).get(ImageShowcaseViewModel::class.java)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageShowcaseViewModel.showcaseItemLiveData.observe(
            viewLifecycleOwner,
            Observer { showcaseItems ->
                Log.d(TAG, "Have showcase items from ViewModel $showcaseItems")
                // TODO: update data backing the recycler view.
            }
        )
    }
}