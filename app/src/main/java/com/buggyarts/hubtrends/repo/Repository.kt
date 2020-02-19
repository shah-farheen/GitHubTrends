package com.buggyarts.hubtrends.repo

import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo

interface Repository {

    suspend fun getTrendingReposFromRemote(): ArrayList<GitHubRepo>?

    suspend fun getLastUpdateTime(): Long

    suspend fun setLastUpdateTime(time: Long)

    suspend fun shouldUpdateFromRemote(): Boolean

    suspend fun getReposFromRemote(): List<GitHubRepo>?
}