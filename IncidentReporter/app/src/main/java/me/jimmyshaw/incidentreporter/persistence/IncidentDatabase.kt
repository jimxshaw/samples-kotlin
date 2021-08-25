package me.jimmyshaw.incidentreporter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import me.jimmyshaw.incidentreporter.models.Incident

@Database(entities = [Incident::class], version = 1)
abstract class IncidentDatabase : RoomDatabase() {
}