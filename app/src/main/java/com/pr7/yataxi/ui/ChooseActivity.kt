package com.pr7.yataxi.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pr7.yataxi.databinding.ActivityChooseBinding
import com.pr7.yataxi.ui.register.RegisterActivity

class ChooseActivity : AppCompatActivity() {
    lateinit var binding: ActivityChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#000000")

        binding.apply {

            buttonpassanger.setOnClickListener {
                startActivity(Intent(this@ChooseActivity, RegisterActivity::class.java))
                finish()

            }
            buttondriver.setOnClickListener {
                startActivity(Intent(this@ChooseActivity, RegisterActivity::class.java))
                finish()
            }
        }

    }
}