package com.pr7.yataxi.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.pr7.yataxi.R
import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.databinding.ActivityLoginBinding
import com.pr7.yataxi.utilits.showlogd
import com.pr7.yataxi.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEBD2F")
        binding.apply {
            buttonregister2.setOnClickListener {
                startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
                finish()

            }
            outlinedTextFieldphonelogin.setText(intent.getStringExtra("phone"))
            outlinedTextFieldpasswordlogin.setText(intent.getStringExtra("password"))

            buttonaclogin.setOnClickListener {
                mainViewModel.loginuser(LoginBody(outlinedTextFieldphonelogin.text.toString(),outlinedTextFieldpasswordlogin.text.toString()))
            }

            mainViewModel.succesregister.observe(this@LoginActivity) {
                if (it) {
                    progressbarlogin.visibility = View.VISIBLE

                } else {
                    progressbarlogin.visibility = View.GONE

                }
            }

            mainViewModel.registerResponseLiveData.observe(this@LoginActivity){
                when(it){
                    is LoginResponse->{
                        if (it.auth_token.isEmpty()){
                            showlogd(it.non_field_errors.get(0))
                        }else{
                            showlogd(it.auth_token)

                        }
                    }
                }
            }

        }



    }
}