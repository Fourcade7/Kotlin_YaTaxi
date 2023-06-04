package com.pr7.yataxi.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.pr7.yataxi.R
import com.pr7.yataxi.viewmodel.MainViewModel

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = Color.parseColor("#FEBD2F")


        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this@SplashActivity,ChooseActivity::class.java))
            finish()

        },3000)
    }
}