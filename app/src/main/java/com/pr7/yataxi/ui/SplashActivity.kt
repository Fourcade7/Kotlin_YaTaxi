package com.pr7.yataxi.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.pr7.yataxi.R
import com.pr7.yataxi.data.pref.DataStoreManager
import com.pr7.yataxi.ui.regions.RegionsActivity
import com.pr7.yataxi.utilits.showlogd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = Color.parseColor("#FEBD2F")
        dataStoreManager= DataStoreManager(this@SplashActivity)

        lifecycleScope.launch {
            dataStoreManager.load("token").collect{token->
                showlogd(token)
                if (token==""){
                    showlogd("if scope $token")

                        delay(3000)
                        startActivity(Intent(this@SplashActivity,ChooseActivity::class.java))
                        finish()

                }else{

                    delay(3000)
                    showlogd("else scope $token")
                    startActivity(Intent(this@SplashActivity, RegionsActivity::class.java))
                    finish()
                }
            }
        }


    }
}