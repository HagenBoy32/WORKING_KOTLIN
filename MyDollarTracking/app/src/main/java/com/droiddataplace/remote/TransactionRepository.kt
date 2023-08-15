package com.droiddataplace.remote

import android.content.Context
import androidx.room.Room
import com.droiddataplace.data.TransActionDataBase
import com.droiddataplace.data.migration_1_2

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
private const val DATABASE_NAME = "transactiondatabase"
class TransactionRepositoryprivate constructor(context: Context) {


    // A Repository is responsible for everything related to persisting data
    private val database: TransActionDataBase = Room.databaseBuilder(
        context.applicationContext,
        TransActionDataBase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

}