package me.jimmyshaw.incidentreporter.persistence

import androidx.room.Dao
import androidx.room.Query
import me.jimmyshaw.incidentreporter.models.Incident
import java.util.*

@Dao
interface IncidentDao {

    @Query("SELECT * FROM incident")
    fun getIncidents(): List<Incident>

    @Query("SELECT * FROM incident WHERE id=(:id)")
    fun getIncident(id: UUID): Incident?
}