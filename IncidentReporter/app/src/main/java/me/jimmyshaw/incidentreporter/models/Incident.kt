package me.jimmyshaw.incidentreporter.models

import java.util.*

data class Incident(
    val id: UUID = UUID.randomUUID(),
    var description: String = "",
    var date: Date = Date(),
    var isResolved: Boolean = false
)