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
import me.jimmyshaw.incidentreporter.R
import me.jimmyshaw.incidentreporter.models.Incident

class IncidentFragment : Fragment() {

    private lateinit var incident: Incident
    private lateinit var descriptionEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var resolvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        incident = Incident()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_incident, container, false)

        descriptionEditText = view.findViewById(R.id.et_incident_description) as EditText
        dateButton = view.findViewById(R.id.btn_incident_date) as Button
        resolvedCheckBox = view.findViewById(R.id.cb_incident_resolved) as CheckBox

        dateButton.apply {
            text = incident.date.toString()
            isEnabled = false
        }

        return view
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
                incident.description = sequence.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // This is left blank deliberately.
            }
        }

        descriptionEditText.addTextChangedListener(descriptionWatcher)

        resolvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                incident.isResolved = isChecked
            }
        }
    }

}