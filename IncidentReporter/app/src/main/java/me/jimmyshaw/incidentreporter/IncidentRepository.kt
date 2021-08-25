package me.jimmyshaw.incidentreporter

import android.content.Context
import java.lang.IllegalStateException

class IncidentRepository private constructor(context: Context) {
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