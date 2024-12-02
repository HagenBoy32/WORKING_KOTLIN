package com.example.studysmart.domain.model

import com.example.studysmart.ui.presentation.gradient1
import com.example.studysmart.ui.presentation.gradient2
import com.example.studysmart.ui.presentation.gradient3
import com.example.studysmart.ui.presentation.gradient4
import com.example.studysmart.ui.presentation.gradient5

data class Subject(

    val name: String,
    val goalHours: Float,
    val colors: List<androidx.compose.ui.graphics.Color>,
    val subjectId: Int = 0

){
   companion object{
       val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
