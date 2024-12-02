package com.example.studysmart.domain.model

data class Task(
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val relatedToSubject: String,
    val isComplete: Boolean = false,
    val taskSubjectId: Int = 0,
    val taskId: Int = 0
)
