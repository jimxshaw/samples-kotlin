package me.jimmyshaw.incidentreporter.viewmodels

import androidx.lifecycle.ViewModel
import me.jimmyshaw.incidentreporter.models.Incident

class IncidentListViewModel : ViewModel() {
    val incidents = mutableListOf<Incident>()

    init {
        for (i in 0 until 50) {
            val incident = Incident()

            incident.title = "Incident $i"
            incident.isResolved = i % 2 == 0
            incidents.add(incident)
        }
    }
}