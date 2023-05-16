package io.github.reskimulud.mynotesapp.data.network.response

import com.google.gson.annotations.SerializedName
import io.github.reskimulud.mynotesapp.model.Note

data class NoteResponse(
    @field:SerializedName("note")
    val note: Note
)