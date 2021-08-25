package me.jimmyshaw.incidentreporter.viewmodels

import androidx.lifecycle.ViewModel
import me.jimmyshaw.incidentreporter.IncidentRepository
import me.jimmyshaw.incidentreporter.models.Incident

class IncidentListViewModel : ViewModel() {

    private val incidentRepository = IncidentRepository.get()
    val incidentListLiveData = incidentRepository.getIncidents()

    fun addIncident(incident: Incident) {
        incidentRepository.addIncident(incident)
    }
}