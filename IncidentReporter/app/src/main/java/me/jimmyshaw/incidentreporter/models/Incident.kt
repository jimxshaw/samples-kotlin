package me.jimmyshaw.incidentreporter.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import java.util.*

@Entity
data class Incident(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isResolved: Boolean = false
)