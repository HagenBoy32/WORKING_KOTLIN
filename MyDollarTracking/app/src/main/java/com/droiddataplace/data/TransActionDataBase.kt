package com.droiddataplace.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.droiddataplace.model.TransactionsData


@Database(entities = [TransactionsData::class], version = 2)
@TypeConverters(TransactionTypeConverters::class)
abstract class TransActionDataBase:  RoomDatabase() {


    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add your migration code here
        }
    }


    abstract fun getTransactionDao(): TransactionDao
    companion object{
        @Volatile
        private var instance: TransActionDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                TransActionDataBase::class.java,
                "transactionsdata_db"
            ).build()

    }


}


