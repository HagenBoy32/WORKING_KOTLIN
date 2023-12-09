package com.droiddataplace.repository

import com.droiddataplace.data.TransActionDataBase
import com.droiddataplace.model.TransactionsData

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
private const val DATABASE_NAME = "transactiondatabase"
class TransactionRepository(private val db: TransActionDataBase) {

    suspend fun insertTransaction(transactionsData: TransactionsData) = db.getTransactionDao().inserttransaction(transactionsData)
    suspend fun deleteTransaction(transactionsData: TransactionsData) = db.getTransactionDao().deleteTransaction(transactionsData)

    fun getAllTransactions() = db.getTransactionDao().getAllTransactions()
 /////   suspend fun searchNote(query: String?) = db.getTransactionDao().searchTransactions(query)
}