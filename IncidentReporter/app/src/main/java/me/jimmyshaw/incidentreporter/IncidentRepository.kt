package me.jimmyshaw.incidentreporter

import android.content.Context
import androidx.room.Room
import me.jimmyshaw.incidentreporter.models.Incident
import me.jimmyshaw.incidentreporter.persistence.IncidentDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "incident-database"

class IncidentRepository private constructor(context: Context) {

    private val database: IncidentDatabase = Room.databaseBuilder(
        context.applicationContext,
        IncidentDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val incidentDao = database.incidentDoa()

    fun getIncidents(): List<Incident> {
        return incidentDao.getIncidents()
    }

    fun getIncident(id: UUID): Incident? {
        return incidentDao.getIncident(id)
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