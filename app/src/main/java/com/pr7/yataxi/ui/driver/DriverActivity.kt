package com.pr7.yataxi.ui.driver

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pr7.yataxi.R
import com.pr7.yataxi.databinding.ActivityDriverBinding


class DriverActivity : AppCompatActivity() {
    lateinit var binding: ActivityDriverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEBD2F")
        val name=intent.getStringExtra("name")
        val directions=intent.getStringExtra("directions")
        val car=intent.getStringExtra("car")
        val phone=intent.getStringExtra("phone")
        val carname=when(car){
            "Malibu"-> R.drawable.malibu
            "Spark"-> R.drawable.spark_green
            "Damas"-> R.drawable.damas
            "Nexia"-> R.drawable.nexia
            "Matiz"-> R.drawable.matiz
            "Gentra"-> R.drawable.gentra
            "Captiva"-> R.drawable.captiva
            "Tracker"-> R.drawable.tracker
            "Cobalt"-> R.drawable.cobalt_red
            "Onix"-> R.drawable.onix
            "Lacetti"-> R.drawable.lacetti
            "Equonix"-> R.drawable.equinox
            "Tahoe"-> R.drawable.tahoe
            "Traverse"-> R.drawable.traverse
            else ->{R.drawable.bus}

        }
        binding.apply {

            textviewcarname.text=car
            textviewdirections.text=directions
            textviewusernamelastname.text=name
            buttonopencallphone.text=phone


            buttonopencallphone.setOnClickListener {
                opencallphone(phone!!)
            }
        }





    }

    fun opencallphone(phone:String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }
}