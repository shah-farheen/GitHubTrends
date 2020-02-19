package com.buggyarts.hubtrends.test

import com.buggyarts.hubtrends.HubTrendsApp
import com.buggyarts.hubtrends.di.component.DaggerApplicationComponent

class HubTrendsTestApp : HubTrendsApp() {

    override fun initializeAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(TestApplicationModule(this))
            .build()
    }
}