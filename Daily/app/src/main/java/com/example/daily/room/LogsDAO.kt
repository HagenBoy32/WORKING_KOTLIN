package com.example.daily.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface LogsDAO {

    @Insert
    suspend fun insertLogs(logs: Logs):Long

    @Update
    suspend fun updateLogs(logs: Logs)

    @Delete
    suspend fun deleteLogs(logs: Logs)

    @Query("DELETE FROM logs")
    suspend fun deleteAll()

    @Query("SELECT * FROM logs")
    fun getAllLogsInDB(): LiveData<List<Logs>>

}