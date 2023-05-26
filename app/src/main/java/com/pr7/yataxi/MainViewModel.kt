package com.pr7.yataxi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel constructor(): ViewModel() {


    var mutableLiveData=MutableLiveData<ArrayList<Int>>()

    val stateflow= MutableStateFlow(0)
    fun insertitem(number: Int){
        stateflow.value=number
    }


    fun additem(number:Int){
        val emptyarraylist:ArrayList<Int> = ArrayList(mutableLiveData.value.orEmpty())
        emptyarraylist.add(number)

        mutableLiveData.value=emptyarraylist
    }
    fun getmutablelivedata():MutableLiveData<ArrayList<Int>>{
        return mutableLiveData
    }
    fun removeitem(number: Int){
        mutableLiveData.value?.remove(number)
        mutableLiveData.value=mutableLiveData.value

    }

}