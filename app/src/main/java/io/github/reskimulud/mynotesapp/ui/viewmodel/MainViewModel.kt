package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import io.github.reskimulud.mynotesapp.model.Note
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NotesRepository): ViewModel() {
    private val _notes = MutableLiveData<List<Note>>(emptyList())
    val notes: LiveData<List<Note>> = _notes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getNotes() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = repository.getNotes()
            when {
                response.isSuccess -> {
                    _notes.postValue(response.getOrNull())
                    _message.postValue("Data berhasil dimuat")
                }
                response.isFailure -> {
                    _notes.postValue(emptyList())
                    _message.postValue("Data berhasil gagal dimuat")
                }
            }
            _isLoading.postValue(false)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearCache()
        }
    }
}