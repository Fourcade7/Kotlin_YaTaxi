package com.pr7.yataxi.data.network

import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.body.RegisterBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.model.response.RegisterResponse


import retrofit2.http.Body
import retrofit2.http.POST


interface Api {


    @POST("auth/users/register")
    suspend fun registerUser(@Body registerBody: RegisterBody):RegisterResponse

    @POST("api-auth/token/login/")
    suspend fun loginUser(@Body loginBody: LoginBody):LoginResponse



}