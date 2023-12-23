package com.droiddataplace.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.droiddataplace.model.TransactionsData
import com.droiddataplace.repository.TransactionRepository
import kotlinx.coroutines.launch
import java.util.UUID

class TransActionViewModel(
      app: Application,
      private val transactionRepository: TransactionRepository) :
      AndroidViewModel(app)

{

    private val transactionIdLiveData = MutableLiveData<UUID>()


  //  var transactionLiveData: LiveData<TransactionsData?> =
  //      Transformations.switchMap(transactionIdLiveData) {transId ->
  //          transactionRepository.getTransaction(transId)

   //     }

    fun loadAccount(transId:UUID){
        transactionIdLiveData.value = transId
    }



    fun addTransaction(transactionsdata: TransactionsData) =
        viewModelScope.launch {
             transactionRepository.insertTransaction(transactionsdata)
            Log.d("<<TransActView>>", "addTransaction: ")

        }

    fun deleteTransaction(transactionsdata: TransactionsData) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(transactionsdata)
        }

    }


    fun getAllTransactions() =  transactionRepository.getAllTransactions()




}