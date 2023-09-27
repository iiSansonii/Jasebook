package com.example.jasebook

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
//endpoints for retrofit to connect
interface api {
    @GET("/post")
    fun getfeed(): Call<ArrayList<post>>
    @Headers("Content-Type:application/json")
    @POST("/signin")
    fun postsignin(@Body info:signingdata):Call<message>

    @GET("/me")
    fun getme():Call<user>

    @GET("/user/")
    fun getuser():Call<user>
    @GET("/user/pepe/posts")
    fun getuserpost():Call<ArrayList<post>>
    @GET("/me/post")
    fun getmypost():Call<ArrayList<post>>
    @POST("/logout")
    fun logout():Call<message>
}