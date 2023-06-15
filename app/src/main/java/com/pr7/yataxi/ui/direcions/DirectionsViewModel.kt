package com.pr7.yataxi.ui.direcions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.yataxi.data.model.response.DriverResponse
import com.pr7.yataxi.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DirectionsViewModel constructor():ViewModel() {
    val mainRepository=MainRepository()
    val succesdriver= MutableLiveData<Boolean>()
    val driverResponseLiveData= MutableLiveData<DriverResponse>()


    fun getAllDrivers(token:String,from:Int,to:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                succesdriver.postValue(true)
                val response=mainRepository.getDrivers(token,from, to)
                driverResponseLiveData.postValue(response)

                succesdriver.postValue(false)
            }catch (e:Exception){
                succesdriver.postValue(false)

            }

        }
    }

}