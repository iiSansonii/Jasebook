package com.example.jasebook

import retrofit2.Response
import retrofit2.http.GET

interface api {
    @GET("/post")
    fun getfeed():Response<List<post>>
}