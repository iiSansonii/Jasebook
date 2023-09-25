package com.example.jasebook

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface api {
    @GET("/post")
    fun getfeed(): Call<ArrayList<post>>
    @Headers("Content-Type:application/json")
    @POST("/signin")
    fun postsignin(@Body info:signingdata):Call<message>

    @GET("/me")
    fun getme():Call<user>
}