package com.example.jasebook

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private val BASE_URL = "https://e97d-91-106-52-179.ngrok-free.app"
    private var mRetrofit: Retrofit? = null


    val client: Retrofit
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return this.mRetrofit!!
        }

}