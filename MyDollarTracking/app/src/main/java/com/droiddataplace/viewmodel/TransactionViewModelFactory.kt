package com.droiddataplace.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droiddataplace.repository.TransactionRepository

class TransactionViewModelFactory (
    val app: Application,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TransActionViewModel(app, transactionRepository) as T
    }
}
