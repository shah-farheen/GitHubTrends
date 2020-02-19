package com.buggyarts.hubtrends.test

import android.app.Application
import com.buggyarts.hubtrends.di.NetworkInfo
import com.buggyarts.hubtrends.di.module.ApplicationModule
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule(private val application: Application) : ApplicationModule(application) {

    @Provides
    @NetworkInfo
    override fun provideBaseUrl(): String {
        return TestUtils.BASE_URL
    }
}