package io.github.reskimulud.mynotesapp.data.network.response

import com.google.gson.annotations.SerializedName
import io.github.reskimulud.mynotesapp.model.Note

data class NotesResponse(
    @field:SerializedName("notes")
    val notes: List<Note>
)