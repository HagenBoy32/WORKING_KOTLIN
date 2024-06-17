package com.example.daily

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


val BASE_URL = "https://fitness-calculator.p.rapidapi.com/"

private val retrofit = Retrofit.Builder()

interface BMIService {

    @GET("imperial")
        fun getBmi(
        @Query("weight") weight: String,
        @Query("height") height: String
        ) : Call<BmiResponse>
    // This is the API the viewmodel will use to communicate with the WebService(RapiApid
    // you should specify the end-points of the url
    // Put the URl build code here.....

    //@GET("/weight")
    //suspend fun getBMI() : Response<HeightsAndWeights>
    //@GET("/height")
    //suspend fun getSpecificHeightsAndWeights(@Query("userId") userId:Int) : Response<HeightsAndWeights>

}


