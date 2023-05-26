package com.pr7.yataxi

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#FEBD2F")

        mainViewModel=ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
        mainViewModel.mutableLiveData.observe(this@MainActivity,{
            Log.d("PR77777", "arraylist size: ${it?.size}")
        })

        GlobalScope.launch {
            mainViewModel.stateflow.collect{
                Log.d("PR77777", "stateflow: $it")

            }

        }
        GlobalScope.launch {
            delay(2000)
            mainViewModel.insertitem(50)
            delay(2000)
            mainViewModel.insertitem(20)
        }



        Handler(Looper.getMainLooper()).postDelayed({

            mainViewModel.additem(10)
            mainViewModel.additem(10)
            Log.d("PR77777", "item added: why nul ?")
            Log.d("PR77777", "arraylist size after item added: ${mainViewModel.getmutablelivedata().value?.size}")


        },3000)
    }
}