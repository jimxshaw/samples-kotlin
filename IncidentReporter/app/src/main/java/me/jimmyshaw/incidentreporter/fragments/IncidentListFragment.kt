package me.jimmyshaw.incidentreporter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.viewmodels.IncidentListViewModel

private const val TAG = "IncidentListFragment"

class IncidentListFragment : Fragment() {

    private lateinit var incidentRecyclerView: RecyclerView

    private val incidentListViewModel: IncidentListViewModel by lazy {
        ViewModelProvider(this).get(IncidentListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total incidents: ${incidentListViewModel.incidents.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_incident_list, container, false)

        incidentRecyclerView = view.findViewById(R.id.rv_incident) as RecyclerView
        incidentRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    companion object {
        fun newInstance(): IncidentListFragment {
            return IncidentListFragment()
        }
    }
}