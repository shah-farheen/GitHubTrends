package com.buggyarts.hubtrends.di.module

import android.app.Application
import android.content.Context
import com.buggyarts.hubtrends.BuildConfig
import com.buggyarts.hubtrends.di.ApplicationContext
import com.buggyarts.hubtrends.di.NetworkInfo
import com.buggyarts.hubtrends.di.PrefsInfo
import com.buggyarts.hubtrends.utils.AppConstants
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    fun providesContext(): Context {
        return mApplication.applicationContext
    }

    @Provides
    @PrefsInfo
    fun providesPrefsName(): String {
        return AppConstants.PREFS_NAME
    }

    @Provides
    @NetworkInfo
    open fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}