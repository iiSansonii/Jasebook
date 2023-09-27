package com.example.jasebook

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

//endpoints for retrofit to connect
interface api {
    @GET("/post")
    fun getfeed(): Call<ArrayList<post>>
    @Headers("Content-Type:application/json")
    @POST("/signin")
    fun postsignin(@Body info:signingdata):Call<message>
    @POST("/signup")
    fun postsignup(@Body info:signupdata):Call<message>
    @GET("/me")
    fun getme():Call<user>

    @GET("/user/{user_name}")
    fun getuser(@Path(value = "user_name",encoded = false)username:String):Call<user>
    @GET("/user/pepe/posts")
    fun getuserpost():Call<ArrayList<post>>
    @GET("/me/post")
    fun getmypost():Call<ArrayList<post>>
    @POST("/logout")
    fun logout():Call<message>

    @POST("/post")
    fun publishapost(@Body info:postbody):Call<message>

    @GET("/post/{post_id}/likes/count")
    fun getnumberoflikes(@Path(value = "post_id",encoded = false)postid:Int):Call<count>
    @GET("/post/{post_id}/comment/count")
    fun getnumberofcomment(@Path(value = "post_id",encoded = false)postid:Int):Call<count>

    @GET("/post/{post_id}/comment")
    fun getcomment(@Path(value = "post_id",encoded = false)postid:Int):Call<ArrayList<comment>>

    @POST("/post/{post_id}/like")
    fun like(@Path(value = "post_id",encoded = false)postid:Int):Call<message>

    @DELETE("/post/{post_id}")
    fun deletepost(@Path(value = "post_id",encoded = false)postid:Int):Call<message>
    @PUT("/post/{post_id}")
    fun editpost(@Path(value="post_id",encoded = false)postid:Int,@Body info:postbody):Call<message>
}