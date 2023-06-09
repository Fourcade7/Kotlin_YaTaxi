package com.pr7.yataxi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.model.response.RegionsResponse
import com.pr7.yataxi.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel constructor():ViewModel() {

    val mainRepository=MainRepository()
    val succesregion= MutableLiveData<Boolean>()
    val regionResponseLiveData= MutableLiveData<RegionsResponse>()


    fun getAllRegions(token:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                succesregion.postValue(true)
                val response=mainRepository.getRegions(token)
                regionResponseLiveData.postValue(response)

                succesregion.postValue(false)
            }catch (e:Exception){
                succesregion.postValue(false)

            }

        }
    }



}