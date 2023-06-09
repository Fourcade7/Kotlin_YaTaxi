package com.pr7.yataxi.data.repository

import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.body.RegisterBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.model.response.RegionsResponse
import com.pr7.yataxi.data.model.response.RegisterResponse


import com.pr7.yataxi.data.network.RetrofitInstance
import com.pr7.yataxi.utilits.Data


class MainRepository {


    suspend fun registeruser(registerBody: RegisterBody): RegisterResponse {

            val response:RegisterResponse= RetrofitInstance.api.registerUser(registerBody)
            return response

    }
    suspend fun loginuser(loginBody: LoginBody): LoginResponse {

        val response:LoginResponse= RetrofitInstance.api.loginUser(loginBody = loginBody)
        return response

    }

    suspend fun getRegions(token:String):RegionsResponse{
        val response=RetrofitInstance.api.getAllRegions("Token $token")
        return response
    }

}