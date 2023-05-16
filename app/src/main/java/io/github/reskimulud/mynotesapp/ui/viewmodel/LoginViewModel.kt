package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: NotesRepository): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = repository.postLogin(username, password)
            when {
                response.isSuccess -> {
                    _message.postValue("Login Berhasil!")
                    _token.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _message.postValue("Gagal Login! ${response.exceptionOrNull()?.message}")
                }
            }
            _isLoading.postValue(false)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            repository.saveUserToken(token)
        }
    }
}