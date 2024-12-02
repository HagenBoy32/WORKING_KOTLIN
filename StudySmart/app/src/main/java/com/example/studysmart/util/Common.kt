package com.example.studysmart.util

import com.example.studysmart.ui.presentation.Green
import com.example.studysmart.ui.presentation.Orange
import com.example.studysmart.ui.presentation.Red
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class  Priority(val title: String, val color: androidx.compose.ui.graphics.Color, val value: Int) {
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Red, value = 2);


    companion object{
        fun fromInt(value: Int) = values().first{it.value == value}
    }

    fun Long?.changeMillisToDateString(): String {
        val date: LocalDate = this?.let {
            Instant
                .ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        } ?: LocalDate.now()
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    }








}