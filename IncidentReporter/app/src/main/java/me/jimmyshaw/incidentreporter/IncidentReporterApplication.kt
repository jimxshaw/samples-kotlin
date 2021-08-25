package me.jimmyshaw.incidentreporter

import android.app.Application

class IncidentReporterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        IncidentRepository.initialize(this)
    }
}