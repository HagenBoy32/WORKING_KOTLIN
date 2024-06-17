package com.example.daily.myviewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daily.room.LogsRepository

class LogsViewModelFactory(private val repository: LogsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LogsViewModel::class.java)){
            return LogsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")


    }
}