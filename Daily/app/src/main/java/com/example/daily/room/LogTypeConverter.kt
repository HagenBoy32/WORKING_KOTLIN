package com.example.daily.room

import android.util.Log
import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class LogTypeConverter {



    @TypeConverter
    fun fromDate(date: Date?): Long? {
        Log.d("<<TransTypeConv", "fromDate: ")
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        Log.d("<<TransTypeConv>>", "toDate: ")
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
















}