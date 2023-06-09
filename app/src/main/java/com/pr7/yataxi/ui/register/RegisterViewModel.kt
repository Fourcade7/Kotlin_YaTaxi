package com.pr7.yataxi.ui.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.yataxi.data.model.body.RegisterBody
import com.pr7.yataxi.data.model.response.RegisterResponse



import com.pr7.yataxi.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel constructor():ViewModel() {

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




}