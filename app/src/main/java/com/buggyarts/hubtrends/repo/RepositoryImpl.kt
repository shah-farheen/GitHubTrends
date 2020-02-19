package com.buggyarts.hubtrends.repo

import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.data.network.NetworkSingleton
import com.buggyarts.hubtrends.data.prefs.PreferencesHelper
import com.buggyarts.hubtrends.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private var preferencesHelper: PreferencesHelper,
    private var networkSingleton: NetworkSingleton
) : Repository {

    override
    suspend fun getTrendingReposFromRemote(): ArrayList<GitHubRepo>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = networkSingleton.gitHubService.getTrendingRepos().execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (ioException: IOException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun getLastUpdateTime(): Long {
        return withContext(Dispatchers.Default) {
            preferencesHelper.getLastUpdateTime()
        }
    }

    override suspend fun setLastUpdateTime(time: Long) {
        withContext(Dispatchers.Default) {
            preferencesHelper.setLastUpdateTime(time)
        }
    }

    override suspend fun shouldUpdateFromRemote(): Boolean {
        val lastUpdateTime = getLastUpdateTime()
        return System.currentTimeMillis() - lastUpdateTime >= AppConstants.UPDATE_INTERVAL
    }

    override suspend fun getReposFromRemote(): List<GitHubRepo>? {
        val response = getTrendingReposFromRemote()
        if (response != null) {
            setLastUpdateTime(System.currentTimeMillis())
        }
        return response
    }
}