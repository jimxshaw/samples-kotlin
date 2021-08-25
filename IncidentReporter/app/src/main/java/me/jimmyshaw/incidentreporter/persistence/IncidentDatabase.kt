package me.jimmyshaw.incidentreporter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.jimmyshaw.incidentreporter.models.Incident

@Database(entities = [Incident::class], version = 1)
@TypeConverters(IncidentTypeConverters::class)
abstract class IncidentDatabase : RoomDatabase() {

    abstract fun incidentDoa(): IncidentDao
}