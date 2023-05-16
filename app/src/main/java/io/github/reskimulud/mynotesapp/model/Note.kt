package io.github.reskimulud.mynotesapp.model

import com.google.gson.annotations.SerializedName

data class Note(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("tags")
    val tags: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String,

    @field:SerializedName("username")
    val username: String? = ""
)
