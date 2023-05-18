package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.launch

class UpdateNoteViewModel(private val repository: NotesRepository): ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isUpdatedSuccess = MutableLiveData<Boolean>()
    val isUpdatedSuccess: LiveData<Boolean> = _isUpdatedSuccess

    fun getNoteById(noteId: String) {
        viewModelScope.launch {
            val response = repository.getNoteById(noteId)
            when {
                response.isSuccess -> {
                    _note.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _message.postValue("Gagal memuat data! ${response.exceptionOrNull()?.message}")
                }
            }
        }
    }

    fun updateNoteById(noteId: String, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.putNoteById(noteId, title, body)
            when {
                response.isSuccess -> {
                    _isUpdatedSuccess.postValue(true)
                    _message.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _isUpdatedSuccess.postValue(false)
                    _message.postValue("Gagal memperbarui catatan! ${response.exceptionOrNull()?.message}")
                }
            }
        }
    }
}