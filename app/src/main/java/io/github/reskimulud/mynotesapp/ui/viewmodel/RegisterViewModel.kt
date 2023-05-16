package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: NotesRepository): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun register(username: String, password: String, fullName: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = repository.postRegister(username, password, fullName)
            when {
                response.isSuccess -> {
                    _message.postValue(response.getOrNull())
                    _isSuccess.postValue(true)
                }
                response.isFailure -> {
                    _message.postValue("Register Gagal! ${response.exceptionOrNull()?.message}")
                    _isSuccess.postValue(false)
                }
            }
            _isLoading.postValue(false)
        }
    }
}