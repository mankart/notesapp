package io.github.reskimulud.mynotesapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("fullname")
    val fullName: String? = ""
)
