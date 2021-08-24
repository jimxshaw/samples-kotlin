package me.jimmyshaw.incidentreporter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.viewmodels.IncidentListViewModel

private const val TAG = "IncidentListFragment"

class IncidentListFragment : Fragment() {

    private lateinit var incidentRecyclerView: RecyclerView

    private val incidentListViewModel: IncidentListViewModel by lazy {
        ViewModelProvider(this).get(IncidentListViewModel::class.java)
    }

    companion object {
        fun newInstance(): IncidentListFragment {
            return IncidentListFragment()
        }
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

    private inner class IncidentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionTextView: TextView = itemView.findViewById(R.id.tv_incident_description)
        val dateTextView: TextView = itemView.findViewById(R.id.tv_incident_date)
    }

    private inner class IncidentAdapter(var incidents: List<Incident>) :
        RecyclerView.Adapter<IncidentHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentHolder {
            val view = layoutInflater.inflate(R.layout.list_item_incident, parent, false)

            return IncidentHolder(view)
        }

        override fun getItemCount(): Int {
            return incidents.size
        }

        override fun onBindViewHolder(holder: IncidentHolder, position: Int) {
            val incident = incidents[position]

            holder.apply {
                descriptionTextView.text = incident.description
                dateTextView.text = incident.date.toString()
            }
        }

    }
}