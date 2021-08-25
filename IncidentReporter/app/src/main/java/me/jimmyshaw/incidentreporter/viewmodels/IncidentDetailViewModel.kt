package me.jimmyshaw.incidentreporter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import me.jimmyshaw.incidentreporter.IncidentRepository
import me.jimmyshaw.incidentreporter.models.Incident
import java.util.*

class IncidentDetailViewModel : ViewModel() {

    private val incidentRepository = IncidentRepository.get()
    private val incidentIdLiveData = MutableLiveData<UUID>()

    // Using a transformation means IncidentFragment only has to observe the
    // exposed incidentLiveData one time. When the fragment changes the ID it
    // wants to display, the ViewModel just publishes the new incident data
    // to the existing live data stream.
    var incidentLiveData: LiveData<Incident?> =
        Transformations.switchMap(incidentIdLiveData) { incidentId ->
            incidentRepository.getIncident(incidentId)
        }

    fun loadIncident(incidentId: UUID) {
        incidentIdLiveData.value = incidentId
    }

    fun saveIncident(incident: Incident) {
        incidentRepository.updateIncident(incident)
    }
}