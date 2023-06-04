package com.pr7.yataxi.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.body.RegisterBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.model.response.RegisterResponse



import com.pr7.yataxi.data.repository.MainRepository
import com.pr7.yataxi.utilits.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Objects

class MainViewModel constructor():ViewModel() {

    val mainRepository=MainRepository()
    val succesregister=MutableLiveData<Boolean>()
    val registerResponseLiveData=MutableLiveData<Any>()





    @SuppressLint("SuspiciousIndentation")
    fun registeruser(registerBody: RegisterBody){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                succesregister.postValue(true)
                Log.d("PR77777", "registeruser: before request")
              val  registerResponse= mainRepository.registeruser(registerBody)


                   registerResponseLiveData.postValue(registerResponse)

                    Log.d("PR77777", "registeruser: after request")
                    succesregister.postValue(false)

            }catch (e:Exception){


                Log.d("PR77777", "registeruser: exception -> error ${e.message}")
                succesregister.postValue(false)
                registerResponseLiveData.postValue(RegisterResponse(
                    first_name = "The phone number entered is not valid.\n or \nUser with this phone number already exists.",
                    id = 0,
                    last_name = "",
                    phone_number = null
                ))

            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    fun loginuser(loginbody: LoginBody){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                succesregister.postValue(true)
                Log.d("PR77777", "registeruser: before request")
                val  loginResponse= mainRepository.loginuser(loginbody)


                registerResponseLiveData.postValue(loginResponse)

                Log.d("PR77777", "registeruser: after request")
                succesregister.postValue(false)

            }catch (e:Exception){

                Log.d("PR77777", "registeruser: exception -> error ${e.message}")
                succesregister.postValue(false)
                registerResponseLiveData.postValue(LoginResponse(non_field_errors = listOf("Unable to log in with provided credentials.")))

            }
        }

    }


}