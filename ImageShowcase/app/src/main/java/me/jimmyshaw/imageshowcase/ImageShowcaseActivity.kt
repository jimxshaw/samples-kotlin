package me.jimmyshaw.imageshowcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.jimmyshaw.imageshowcase.fragments.ImageShowcaseFragment

class ImageShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_showcase)

        // Check whether a fragment is already hosted.
        // If the bundle is null then this is a fresh launch of the activity and
        // we can safely assume no fragments were automatically restored and re-hosted.
        // If the bundle is not null then it means the activity got destroyed, such as
        // through rotation or process death, and any fragments that were hosted before
        // the destruction were re-created and added back to their respective containers.
        val fragmentContainerIsEmpty: Boolean = savedInstanceState == null

        if (fragmentContainerIsEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ImageShowcaseFragment.newInstance())
                .commit()
        }

    }
}