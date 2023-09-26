package com.example.jasebook

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

//init retrofit
object RetrofitInstance {


    private val BASE_URL = "http://192.168.1.3:8000"
    private var mRetrofit: Retrofit? = null

    val c = OkHttpClient.Builder()
        .cookieJar(cookiejar())
        .build()

    val client: Retrofit
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(c)
                    .build()
            }
            return this.mRetrofit!!
        }

}