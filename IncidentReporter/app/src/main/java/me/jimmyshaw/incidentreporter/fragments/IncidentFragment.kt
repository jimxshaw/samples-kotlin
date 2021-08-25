package me.jimmyshaw.incidentreporter.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.viewmodels.IncidentDetailViewModel
import java.util.*

private const val TAG = "IncidentFragment"
private const val ARG_INCIDENT_ID = "incident_id"

class IncidentFragment : Fragment() {

    private lateinit var incident: Incident
    private lateinit var titleEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var resolvedCheckBox: CheckBox

    private val incidentDetailViewModel: IncidentDetailViewModel by lazy {
        ViewModelProvider(this).get(IncidentDetailViewModel::class.java)
    }

    companion object {
        fun newInstance(incidentId: UUID): IncidentFragment {
            val args = Bundle().apply {
                putSerializable(ARG_INCIDENT_ID, incidentId)
            }

            return IncidentFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        incident = Incident()

        val incidentId: UUID = arguments?.getSerializable(ARG_INCIDENT_ID) as UUID

        incidentDetailViewModel.loadCrime(incidentId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_incident, container, false)

        titleEditText = view.findViewById(R.id.et_incident_title) as EditText
        dateButton = view.findViewById(R.id.btn_incident_date) as Button
        resolvedCheckBox = view.findViewById(R.id.cb_incident_resolved) as CheckBox

        dateButton.apply {
            text = incident.date.toString()
            isEnabled = false
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        incidentDetailViewModel.incidentLiveData.observe(
            viewLifecycleOwner,
            Observer { incident ->
                incident?.let {
                    this.incident = incident
                    updateUI()
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()

        val descriptionWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This is left blank deliberately.
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                incident.title = sequence.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // This is left blank deliberately.
            }
        }

        titleEditText.addTextChangedListener(descriptionWatcher)

        resolvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                incident.isResolved = isChecked
            }
        }
    }

    private fun updateUI() {
        titleEditText.setText(incident.title)
        dateButton.text = incident.date.toString()
        resolvedCheckBox.apply {
            isChecked = incident.isResolved
            // Skip the checkbox getting checked animation by
            // programmatically setting the checkbox to checked.
            jumpDrawablesToCurrentState()
        }
    }

}