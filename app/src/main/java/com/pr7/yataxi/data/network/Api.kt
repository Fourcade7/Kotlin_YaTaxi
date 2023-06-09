package com.pr7.yataxi.data.network

import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.body.RegisterBody
import com.pr7.yataxi.data.model.response.DriverResponse
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.model.response.RegionsResponse
import com.pr7.yataxi.data.model.response.RegisterResponse


import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface Api {


    @POST("auth/users/register")
    suspend fun registerUser(@Body registerBody: RegisterBody):RegisterResponse

    @POST("api-auth/token/login/")
    suspend fun loginUser(@Body loginBody: LoginBody):LoginResponse
    //http://185.195.24.206:8000/api/cars/
    @GET("api/regions/")
    suspend fun getAllRegions(@Header("Authorization") token:String):RegionsResponse

    @GET("api/filter-directions/")
    suspend fun getDrivers(@Query("direction_from") from:Int,@Query("direction_to") to:Int):DriverResponse



}