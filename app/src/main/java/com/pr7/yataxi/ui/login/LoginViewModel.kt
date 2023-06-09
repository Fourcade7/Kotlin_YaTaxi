package com.pr7.yataxi.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel constructor(): ViewModel() {
    val mainRepository= MainRepository()
    val succeslogin= MutableLiveData<Boolean>()
    val loginResponseLiveData= MutableLiveData<LoginResponse>()

    @SuppressLint("SuspiciousIndentation")
    fun loginuser(loginbody: LoginBody){

        viewModelScope.launch(Dispatchers.IO) {
            try {
               succeslogin.postValue(true)
                Log.d("PR77777", "loginuser: before request")
                val  loginResponse= mainRepository.loginuser(loginbody)


                loginResponseLiveData.postValue(loginResponse)

                Log.d("PR77777", "loginuser: after request")
               succeslogin.postValue(false)

            }catch (e:Exception){

                Log.d("PR77777", "loginuser: exception -> error ${e.message}")
                succeslogin.postValue(false)
                loginResponseLiveData.postValue(LoginResponse(non_field_errors = listOf("Unable to log in with provided credentials.")))

            }
        }

    }
}