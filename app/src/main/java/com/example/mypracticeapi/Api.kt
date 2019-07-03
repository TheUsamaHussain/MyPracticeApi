package com.example.mypracticeapi

import com.example.mypracticeapi.models.getData.GetLogin
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.*


class Api{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pocketmart.pk/api/usama/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Main::class.java)
    interface Main{

        @FormUrlEncoded
        @POST("login")
        fun login(@Field("username") username: String, @Field("password") password: String): Call<LoginResponse>

        @FormUrlEncoded
        @POST("register")
        fun register(@Field("name") name: String, @Field("email") email: String, @Field("username") username: String, @Field("password") password: String, @Field("age") age: Int): Call<LoginResponse>

        @GET("getUser/{username}")
        fun getUser(@Path("username") username: String): Call<UserRec>

    }


    data class LoginResponse(val message:String,val error:Boolean,val data: GetLogin)
    data class UserRec(val message:String,val error:Boolean,val data: Array<GetLogin>)
}