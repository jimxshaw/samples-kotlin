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

    var incidentLiveData: LiveData<Incident?> =
        Transformations.switchMap(incidentIdLiveData) { incidentId ->
            incidentRepository.getIncident(incidentId)
        }

    fun loadCrime(incidentId: UUID) {
        incidentIdLiveData.value = incidentId
    }
}