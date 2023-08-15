package com.droiddataplace.data
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.droiddataplace.data.TransactionsData
abstract class TransActionDataBase:  RoomDatabase() {


    abstract fun TransactionDao(): TransactionDao

}


val migration_1_2 = object : Migration(1, 1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        Log.d("<<:*)>>", "<<TransactionDatabase>><<TransactionDataBase>> ")
        database.execSQL(
            "ALTER TABLE Transaction ADD COLUMN Paid''"
        )
    }
}