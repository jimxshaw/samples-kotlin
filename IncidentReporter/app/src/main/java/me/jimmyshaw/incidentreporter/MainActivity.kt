package me.jimmyshaw.incidentreporter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.jimmyshaw.incidentreporter.fragments.IncidentFragment
import me.jimmyshaw.incidentreporter.fragments.IncidentListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = IncidentListFragment.newInstance()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}