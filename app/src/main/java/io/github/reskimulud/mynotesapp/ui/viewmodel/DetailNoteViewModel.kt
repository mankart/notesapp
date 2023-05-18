package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.launch

class DetailNoteViewModel(private val repository: NotesRepository): ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isNoteDeleted = MutableLiveData<Boolean>()
    val isNoteDeleted: LiveData<Boolean> = _isNoteDeleted

    fun getNoteById(noteId: String) {
        viewModelScope.launch {
            val response = repository.getNoteById(noteId)
            when {
                response.isSuccess -> {
                    _note.postValue(response.getOrNull())
                    _message.postValue("Data berhasil dimuat!")
                }
                response.isFailure -> {
                    _message.postValue("Data gagal dimuat! ${response.exceptionOrNull()?.message}")
                }
            }
        }
    }

    fun deleteNoteById(noteId: String) {
        viewModelScope.launch {
            val response = repository.deleteNoteById(noteId)
            when {
                response.isSuccess -> {
                    _isNoteDeleted.postValue(true)
                    _message.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _isNoteDeleted.postValue(false)
                    _message.postValue("Gagal Menghapus Catatan! ${response.exceptionOrNull()?.message}")
                }
            }
        }
    }
}