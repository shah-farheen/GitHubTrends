package com.buggyarts.hubtrends.di.component

import com.buggyarts.hubtrends.HubTrendsApp
import com.buggyarts.hubtrends.data.network.NetworkSingleton
import com.buggyarts.hubtrends.data.prefs.PreferencesHelper
import com.buggyarts.hubtrends.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: HubTrendsApp)

    fun getPreferencesHelper(): PreferencesHelper

    fun getNetworkSingleton(): NetworkSingleton
}