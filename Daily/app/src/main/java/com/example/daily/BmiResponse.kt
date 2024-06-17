package com.example.daily


data class BmiResponse(
    val bmi: Float,
    val health: String,
    val healthy_bmi_range: String
)