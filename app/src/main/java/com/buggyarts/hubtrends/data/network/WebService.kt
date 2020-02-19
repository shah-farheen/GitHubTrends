package com.buggyarts.hubtrends.data.network

import com.buggyarts.hubtrends.data.model.developers.GitHubDeveloper
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("repositories")
    fun getTrendingRepos(): Call<ArrayList<GitHubRepo>>

    @GET("developers")
    fun getDevelopers(): Call<ArrayList<GitHubDeveloper>>
}