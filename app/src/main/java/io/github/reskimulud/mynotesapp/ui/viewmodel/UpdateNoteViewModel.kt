package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.launch

class UpdateNoteViewModel(private val repository: NotesRepository): ViewModel() {
    // TODO : ViewModel untuk UpdateNoteActivity
}