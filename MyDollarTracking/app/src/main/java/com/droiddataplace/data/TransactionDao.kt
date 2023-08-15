package com.droiddataplace.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

interface TransactionDao {

    @Query("SELECT * FROM TransactionsData")

    fun getAccounts(): LiveData<List<TransactionsData>>


    @Query("SELECT * FROM money WHERE id=(:id)")
    fun getAccount(id: UUID): LiveData<TransactionsData?>

    @Update
    fun updateAccount(account: TransactionsData)

    @Insert
    fun addAccount(account: TransactionsData)

    @Query("DELETE FROM transaction")
    fun deleteAll()







}