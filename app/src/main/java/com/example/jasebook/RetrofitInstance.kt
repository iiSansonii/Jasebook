package com.example.jasebook

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val api:api by lazy {
        Retrofit.Builder().baseUrl("https://192.168.1.3:8000")
            .addConverterFactory(GsonConverterFactory.create()).build().create(api::class.java)
    }
}