package me.jimmyshaw.incidentreporter.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.viewmodels.IncidentListViewModel
import java.util.*

private const val TAG = "IncidentListFragment"

class IncidentListFragment : Fragment() {

    /*
     * Required interface for hosting activities.
     */
    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is crucial as it allows the FragmentManager to
        // know that IncidentListFragment needs to receive callbacks.
        setHasOptionsMenu(true)
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

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_incident_list, menu)
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
            callbacks?.onCrimeSelected(incident.id)
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