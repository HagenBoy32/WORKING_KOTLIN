package com.example.daily

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("x-rapidapi-key", "5973dc0144mshb883152bebd01e8p1e4863jsne1c5f43cb429")
                .header("x-rapidapi-host", "body-mass-index-bmi-calculator.p.rapidapi.com")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://body-mass-index-bmi-calculator.p.rapidapi.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T {
        Log.d("<<RetrofitInstance>>", "create: ")
        return retrofit.create(service)
    }
}

