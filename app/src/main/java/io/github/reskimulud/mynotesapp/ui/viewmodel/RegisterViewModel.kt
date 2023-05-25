package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.reskimulud.mynotesapp.data.NotesRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: NotesRepository): ViewModel() {
    // TODO : ViewModel untuk activity Register
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun register(username: String, password: String, fullName: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = repository.postRegister(username, password, fullName)
            when {
                response.isSuccess -> {
                    _isSuccess.postValue(true)
                    _message.postValue(response.getOrNull())
                }
                response.isFailure -> {
                    _isSuccess.postValue(false)
                    _message.postValue("Register Gagal!")
                }
            }
            _isLoading.postValue(false)
        }
    }
}