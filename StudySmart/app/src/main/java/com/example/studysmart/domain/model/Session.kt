package com.example.studysmart.domain.model

data class Session(
    val sessionSubjectId: Int,
    val relatedToSubject: String,
    val date: Long,
    val duration: Int,
    val sessionId: Int,
)
