package com.pr7.yataxi.ui.driver

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pr7.yataxi.R
import com.pr7.yataxi.databinding.ActivityDriverBinding


class DriverActivity : AppCompatActivity() {
    lateinit var binding: ActivityDriverBinding
    var cliced=false
    var cliced2=false
    var cliced3=false
    var cliced4=false
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
            imageviewcaravatarss.setImageResource(carname)


            buttonopencallphone.setOnClickListener {
                opencallphone(phone!!)
            }

            linearlayout1.setOnClickListener {
                cliced=!cliced
                if (cliced){
                    linearlayout1.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_green)
                    seat1.setImageResource(R.drawable.seatgreen)
                }else{
                    linearlayout1.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_white)
                    seat1.setImageResource(R.drawable.seatyellow)
                }
            }
            linearlayout2.setOnClickListener {
                cliced2=!cliced2
                if (cliced2){
                    linearlayout2.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_green)
                    seat2.setImageResource(R.drawable.seatgreen)
                }else{
                    linearlayout2.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_white)
                    seat2.setImageResource(R.drawable.seatyellow)
                }
            }
            linearlayout3.setOnClickListener {
                cliced3=!cliced3
                if (cliced3){
                    linearlayout3.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_green)
                    seat3.setImageResource(R.drawable.seatgreen)
                }else{
                    linearlayout3.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_white)
                    seat3.setImageResource(R.drawable.seatyellow)
                }
            }
            linearlayout4.setOnClickListener {
                cliced4=!cliced4
                if (cliced4){
                    linearlayout4.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_green)
                    seat4.setImageResource(R.drawable.seatgreen)
                }else{
                    linearlayout4.background=ContextCompat.getDrawable(this@DriverActivity,R.drawable.layout_select_white)
                    seat4.setImageResource(R.drawable.seatyellow)
                }
            }

        }





    }

    fun opencallphone(phone:String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }
}