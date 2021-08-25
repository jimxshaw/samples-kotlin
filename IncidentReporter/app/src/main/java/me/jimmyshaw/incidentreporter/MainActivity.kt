package me.jimmyshaw.incidentreporter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.jimmyshaw.incidentreporter.fragments.IncidentListFragment
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), IncidentListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = IncidentListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        Log.d(TAG, "MainActivity.onCrimeSelected: $crimeId")
    }
}