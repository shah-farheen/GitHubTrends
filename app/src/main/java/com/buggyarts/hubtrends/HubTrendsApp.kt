package com.buggyarts.hubtrends

import android.app.Application
import com.buggyarts.hubtrends.di.component.ApplicationComponent
import com.buggyarts.hubtrends.di.component.DaggerApplicationComponent
import com.buggyarts.hubtrends.di.module.ApplicationModule

open class HubTrendsApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeAppComponent()
        applicationComponent.inject(this)
    }

    open fun initializeAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}