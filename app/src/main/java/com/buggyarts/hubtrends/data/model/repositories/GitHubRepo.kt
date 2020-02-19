package com.buggyarts.hubtrends.data.model.repositories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GitHubRepo(

    @field:SerializedName("author")
    @field:Expose
    var author: String?,

    @field:SerializedName("name")
    @field:Expose
    var name: String?,

    @field:SerializedName("avatar")
    @field:Expose
    var avatar: String?,

    @field:SerializedName("url")
    @field:Expose
    var url: String?,

    @field:SerializedName("description")
    @field:Expose
    var description: String?,

    @field:SerializedName("language")
    @field:Expose
    var language: String?,

    @field:SerializedName("languageColor")
    @field:Expose
    var languageColor: String?,

    @field:SerializedName("stars")
    @field:Expose
    var stars: Int,

    @field:SerializedName("forks")
    @field:Expose
    var forks: Int,

    @field:SerializedName("currentPeriodStars")
    @field:Expose
    var currentPeriodStars: Int,

    @field:SerializedName("builtBy")
    @field:Expose
    var builtBy: ArrayList<BuiltBy>?
) {

    class NamesComparator : Comparator<GitHubRepo> {
        override fun compare(o1: GitHubRepo?, o2: GitHubRepo?): Int {
            return o1!!.name!!.compareTo(o2!!.name!!, ignoreCase = true)
        }
    }

    class StarsComparator : Comparator<GitHubRepo> {
        override fun compare(o1: GitHubRepo?, o2: GitHubRepo?): Int {
            return o2!!.stars.compareTo(o1!!.stars)
        }
    }
}
