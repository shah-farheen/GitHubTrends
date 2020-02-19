package com.buggyarts.hubtrends.data.model.developers

import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GitHubDeveloper(
    @field:SerializedName("username")
    @field:Expose
    var username: String,

    @field:SerializedName("name")
    @field:Expose
    var name: String,

    @field:SerializedName("url")
    @field:Expose
    var url: String,

    @field:SerializedName("avatar")
    @field:Expose
    var avatar: String,

    @field:SerializedName("repo")
    @field:Expose
    var repo: GitHubRepo
)