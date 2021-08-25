package me.jimmyshaw.incidentreporter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.viewmodels.IncidentListViewModel

private const val TAG = "IncidentListFragment"

class IncidentListFragment : Fragment() {

    private lateinit var incidentRecyclerView: RecyclerView

    // Initialize with an empty list because this fragment will
    // have to wait for the database results before it
    // can populate the recycler view.
    private var adapter: IncidentAdapter? = IncidentAdapter(emptyList())

    private val incidentListViewModel: IncidentListViewModel by lazy {
        ViewModelProvider(this).get(IncidentListViewModel::class.java)
    }

    companion object {
        fun newInstance(): IncidentListFragment {
            return IncidentListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_incident_list, container, false)

        incidentRecyclerView = view.findViewById(R.id.rv_incident) as RecyclerView
        incidentRecyclerView.layoutManager = LinearLayoutManager(context)
        incidentRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This observe function is used to register an observer on the LiveData
        // instance and tie the life of the observation to the life of this fragment.
        incidentListViewModel.incidentListLiveData.observe(
            // Observe the LiveData to ensure that the view is ready to display
            // the incident data. Using the view's lifecycle owner ensures we
            // will not receive updates when the view is not on the screen.
            viewLifecycleOwner,
            // This Observer implementation is responsible for
            // reacting to new data from the LiveData.
            Observer { incidents ->
                incidents?.let {
                    Log.i(TAG, "Got incidents ${incidents.size}")
                    updateUI(incidents)
                }
            }
        )
    }

    private fun updateUI(incidents: List<Incident>) {
        adapter = IncidentAdapter(incidents)
        incidentRecyclerView.adapter = adapter
    }

    private inner class IncidentHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var incident: Incident

        private val titleTextView: TextView =
            itemView.findViewById(R.id.tv_incident_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.tv_incident_date)
        private val resolvedImageView: ImageView = itemView.findViewById(R.id.iv_incident_resolved)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(incident: Incident) {
            this.incident = incident

            titleTextView.text = this.incident.title
            dateTextView.text = this.incident.date.toString()

            resolvedImageView.visibility = if (incident.isResolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${incident.title} pressed!", Toast.LENGTH_SHORT).show()
        }
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
            holder.bind(incident)
        }

    }
}