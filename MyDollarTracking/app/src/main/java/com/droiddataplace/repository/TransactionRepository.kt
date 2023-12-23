package com.droiddataplace.repository

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.droiddataplace.data.TransActionDataBase
import com.droiddataplace.data.TransactionDao
import com.droiddataplace.model.TransactionsData
import java.util.UUID



//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
private const val DATABASE_NAME = "transactiondatabase"
class TransactionRepository(private val db: TransActionDataBase) {

  //  private val database: TransActionDataBase = Room.databaseBuilder(
  //      CompletedContinuation.context.applicationContext,
  //      TransActionDataBase::class.java,
  //      DATABASE_NAME
  //  ).addMigrations(migration_1_2)
  //     .build()

    suspend fun insertTransaction(transactionsData: TransactionsData) = db.getTransactionDao().inserttransaction(transactionsData)
    suspend fun deleteTransaction(transactionsData: TransactionsData) = db.getTransactionDao().deleteTransaction(transactionsData)

 //  fun getTransaction(id: UUID):  LiveData<TransactionsData?> = trans


    fun getAllTransactions() = db.getTransactionDao().getAllTransactions()
 /////   suspend fun searchNote(query: String?) = db.getTransactionDao().searchTransactions(query)
}