package io.github.reskimulud.mynotesapp.ui.viewmodel

import androidx.lifecycle.*
import io.github.reskimulud.mynotesapp.data.NotesRepository

class SplashScreenViewModel(private val repository: NotesRepository): ViewModel() {
    fun getUserToken(): LiveData<String> {
        return repository.getUserToken().asLiveData()
    }
}