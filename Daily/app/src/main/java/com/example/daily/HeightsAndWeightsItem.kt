package com.example.daily

import com.google.gson.annotations.SerializedName

data class HeightsAndWeightsItem(
    @SerializedName("weight")
    val weight:Int,
    @SerializedName("height")
    val heinght:Int
    )
