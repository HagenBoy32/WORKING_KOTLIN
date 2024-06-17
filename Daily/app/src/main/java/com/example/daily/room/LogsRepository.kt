package com.example.daily.room

class LogsRepository(private val dao:  LogsDAO) {

    val logs = dao.getAllLogsInDB()

    suspend fun insert(logs: Logs): Long{
        return dao.insertLogs(logs)
    }

    suspend fun delete(logs: Logs) {
        return dao.deleteLogs(logs)
    }
    suspend fun update(logs: Logs ) {
        return dao.updateLogs(logs)
    }

    suspend fun deleteAll(){
        return dao.deleteAll()
    }


}