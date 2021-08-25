package me.jimmyshaw.incidentreporter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.jimmyshaw.incidentreporter.fragments.IncidentFragment
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

    override fun onIncidentSelected(crimeId: UUID) {
        val fragment = IncidentFragment.newInstance(crimeId)

        // The Fragment must be added to the backstack in order for
        // the Back Button to go back to the IncidentListFragment. Otherwise,
        // the Back Button will dismiss MainActivity.
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}