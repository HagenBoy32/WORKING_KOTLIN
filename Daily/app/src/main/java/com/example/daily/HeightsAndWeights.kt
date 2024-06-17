package com.example.daily

import com.google.gson.annotations.SerializedName

data class HeightsAndWeights (
    @SerializedName("weight")
    val weight: Int,

    @SerializedName("height")
    val height: Int,

)
