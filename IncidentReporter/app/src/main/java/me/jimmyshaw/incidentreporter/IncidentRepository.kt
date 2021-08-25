package me.jimmyshaw.incidentreporter

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.persistence.IncidentDatabase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "incident-database"

class IncidentRepository private constructor(context: Context) {

    private val database: IncidentDatabase = Room.databaseBuilder(
        context.applicationContext,
        IncidentDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val incidentDao = database.incidentDoa()

    // Use executor to push operations off the main thread into background threads.
    private val executor = Executors.newSingleThreadExecutor()

    fun getIncidents(): LiveData<List<Incident>> {
        return incidentDao.getIncidents()
    }

    fun getIncident(id: UUID): LiveData<Incident?> {
        return incidentDao.getIncident(id)
    }

    fun updateIncident(incident: Incident) {
        executor.execute {
            incidentDao.updateIncident(incident)
        }
    }

    fun addIncident(incident: Incident) {
        executor.execute {
            incidentDao.addIncident(incident)
        }
    }

    companion object {
        private var INSTANCE: IncidentRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = IncidentRepository(context)
            }
        }

        fun get(): IncidentRepository {
            return INSTANCE ?: throw IllegalStateException("IncidentRepository must be initialized")
        }
    }
}