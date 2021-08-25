package me.jimmyshaw.incidentreporter.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import me.jimmyshaw.incidentreporter.models.Incident
import java.util.*

@Dao
interface IncidentDao {

    @Query("SELECT * FROM incident")
    fun getIncidents(): LiveData<List<Incident>>

    @Query("SELECT * FROM incident WHERE id=(:id)")
    fun getIncident(id: UUID): LiveData<Incident?>

    @Update
    fun updateIncident(incident: Incident)

    @Insert
    fun addIncident(incident: Incident)
}