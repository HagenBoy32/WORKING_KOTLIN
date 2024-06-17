package com.example.daily.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Logs::class], version =4)
@TypeConverters(LogTypeConverter::class)
abstract class LogsDatabase : RoomDatabase() {

    abstract val logsDAO: LogsDAO


    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add your migration code here
        }
    }



    // Singleton Design Pattern
    companion object {
        @Volatile
        private var INSTANCE: LogsDatabase? = null
        fun getInstance(context: Context): LogsDatabase {

            synchronized(this) {
                var instance = INSTANCE


                if (instance == null) {
                    // Creating the database object
                    instance = databaseBuilder(
                        context.applicationContext,
                        LogsDatabase::class.java,
                        "logs_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance

            }
        }
    }
}