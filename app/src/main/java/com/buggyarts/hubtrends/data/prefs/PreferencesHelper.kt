package com.buggyarts.hubtrends.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.buggyarts.hubtrends.di.ApplicationContext
import com.buggyarts.hubtrends.di.PrefsInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(
    @ApplicationContext var context: Context,
    @PrefsInfo var prefsName: String
) {

    companion object {
        const val LAST_UPDATE_TIME = "LAST_UPDATE_TIME"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun getLastUpdateTime(): Long {
        return mPrefs.getLong(LAST_UPDATE_TIME, 0)
    }

    fun setLastUpdateTime(time: Long) {
        mPrefs.edit().putLong(LAST_UPDATE_TIME, time).apply()
    }
}