package com.droiddataplace.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.repository.TransactionRepository
import kotlinx.coroutines.launch

class TransActionViewModel(
      app: Application,
      private val transactionRepository: TransactionRepository) :
      AndroidViewModel(app)

{

    fun addTransaction(transactionsdata: TransactionsData) =
        viewModelScope.launch {
             transactionRepository.insertTransaction(transactionsdata)

        }

    fun deleteTransaction(transactionsdata: TransactionsData) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(transactionsdata)
        }

    }


    fun getAllTransactions() =  transactionRepository.getAllTransactions()


   // suspend fun searchTransaction(query : String?) =
     //   transactionRepository.searchNote(query)





}