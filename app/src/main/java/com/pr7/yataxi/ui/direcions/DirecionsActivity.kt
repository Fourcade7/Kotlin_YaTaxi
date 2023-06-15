package com.pr7.yataxi.ui.direcions

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pr7.yataxi.databinding.ActivityDirectionsBinding

import com.pr7.yataxi.utilits.alertdialogprogressbar
import com.pr7.yataxi.utilits.showlogd

class DirecionsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDirectionsBinding
    var directionfrom: Int? =null
    var directionto:Int?=null
    var token:String?=null

    lateinit var directionsAdapter: DirectionsAdapter

    val directionsViewModel: DirectionsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDirectionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEBD2F")

        var mydialog=alertdialogprogressbar(Dialog(this))
        directionfrom=intent.getIntExtra("from",0)
        directionto=intent.getIntExtra("to",0)
        token=intent.getStringExtra("token")

        directionsViewModel.getAllDrivers(token = token!!, from = directionfrom!!, to = directionto!!)
        directionsViewModel.driverResponseLiveData.observe(this@DirecionsActivity){
            if (it.results.isEmpty()){
                showlogd("bu yo`nalish bo`yicha taxi xizmati mavjud emas")
                binding.imageviewnodiercions.visibility=View.VISIBLE

            }
            directionsAdapter= DirectionsAdapter(this@DirecionsActivity,it.results)
            binding.apply {
                recyclerviewdrivers.layoutManager=LinearLayoutManager(this@DirecionsActivity)
                recyclerviewdrivers.adapter=directionsAdapter
            }

        }
        directionsViewModel.succesdriver.observe(this@DirecionsActivity){
            if (it){
                mydialog.show()
            }else{
                mydialog.dismiss()
            }
        }











    }
}