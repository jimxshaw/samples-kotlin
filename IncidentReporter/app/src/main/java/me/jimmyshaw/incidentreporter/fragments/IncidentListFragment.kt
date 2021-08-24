package me.jimmyshaw.incidentreporter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.jimmyshaw.incidentreporter.viewmodels.IncidentListViewModel

private const val TAG = "IncidentListFragment"

class IncidentListFragment : Fragment() {

    private val incidentListViewModel: IncidentListViewModel by lazy {
        ViewModelProvider(this).get(IncidentListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total incidents: ${incidentListViewModel.incidents.size}")
    }

    companion object {
        fun newInstance(): IncidentListFragment {
            return IncidentListFragment()
        }
    }
}