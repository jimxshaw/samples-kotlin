package me.jimmyshaw.incidentreporter.persistence

import androidx.room.TypeConverter
import java.util.*

// A type converter class will tell Room how
// to convert specific types in to a format
// that can be stored in the database.
class IncidentTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisecondsSinceEpoch: Long?): Date? {
        return millisecondsSinceEpoch?.let { milliseconds ->
            Date(milliseconds)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}