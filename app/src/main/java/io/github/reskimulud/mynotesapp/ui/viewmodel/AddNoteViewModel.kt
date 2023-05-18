package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NotesRepository): ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun addNote(title: String, body: String) {
        viewModelScope.launch {
            val response = repository.postNote(title, body)
            when {
                response.isSuccess -> {
                    _isSuccess.postValue(true)
                    _message.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _isSuccess.postValue(false)
                    _message.postValue("Gagal menambahkan catatan!. ${response.exceptionOrNull()?.message}")
                }
            }
        }
    }
}