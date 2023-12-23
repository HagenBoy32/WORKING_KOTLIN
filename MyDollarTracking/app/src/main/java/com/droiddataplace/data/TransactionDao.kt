package com.droiddataplace.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.droiddataplace.model.TransactionsData
import java.util.*
@Dao
interface TransactionDao {

   // @Query("SELECT * FROM TransactionsData")
   // suspend fun getAccounts(): LiveData<List<TransactionsData>>


    @Query("SELECT * FROM transactionsdata ORDER BY id DESC")
    fun getAllTransactions(): LiveData<List<TransactionsData>>

    @Query("SELECT * FROM transactionsdata WHERE id=(:id)")
    fun getTransaction(id:UUID): LiveData<TransactionsData?>

    @Update
    suspend fun updateTransaction(transaction: TransactionsData)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionsData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttransaction(transaction: TransactionsData)

    @Query("DELETE FROM TRANSACTIONSDATA")
    suspend fun deleteAll()




}