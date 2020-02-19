package com.buggyarts.hubtrends.data.model.repositories

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BuiltBy(

    @field:SerializedName("username")
    @field:Expose
    var username: String?,

    @field:SerializedName("href")
    @field:Expose
    var href: String?,

    @field:SerializedName("avatar")
    @field:Expose
    var avatar: String?
)
