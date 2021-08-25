package me.jimmyshaw.incidentreporter.viewmodels

import androidx.lifecycle.ViewModel
import me.jimmyshaw.incidentreporter.IncidentRepository

class IncidentListViewModel : ViewModel() {

    private val incidentRepository = IncidentRepository.get()
    val incidentListLiveData = incidentRepository.getIncidents()
}