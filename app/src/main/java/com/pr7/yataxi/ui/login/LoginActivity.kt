package com.pr7.yataxi.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pr7.yataxi.data.model.body.LoginBody
import com.pr7.yataxi.data.model.response.LoginResponse
import com.pr7.yataxi.data.pref.DataStoreManager
import com.pr7.yataxi.databinding.ActivityLoginBinding
import com.pr7.yataxi.ui.home.HomeActivity
import com.pr7.yataxi.ui.register.RegisterActivity
import com.pr7.yataxi.utilits.showlogd
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEBD2F")
        dataStoreManager = DataStoreManager(this@LoginActivity)
        var first_name = intent.getStringExtra("first_name")
        var last_name = intent.getStringExtra("last_name")
        var phone = intent.getStringExtra("phone")
        var password = intent.getStringExtra("password")

        binding.apply {

            buttonregister2.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()

            }
            outlinedTextFieldphonelogin.setText(phone)
            outlinedTextFieldpasswordlogin.setText(password)

            buttonaclogin.setOnClickListener {
                loginViewModel.loginuser(
                    LoginBody(
                        outlinedTextFieldphonelogin.text.toString(),
                        outlinedTextFieldpasswordlogin.text.toString()
                    )
                )
            }

            loginViewModel.succeslogin.observe(this@LoginActivity) {
                if (it) {
                    progressbarlogin.visibility = View.VISIBLE

                } else {
                    progressbarlogin.visibility = View.GONE

                }
            }

            loginViewModel.loginResponseLiveData.observe(this@LoginActivity) {

                if (it.auth_token.isEmpty()) {
                    showlogd(it.non_field_errors.get(0))
                } else {
                    showlogd(it.auth_token)
                    lifecycleScope.launch {
                        dataStoreManager.save(key = "token", value = it.auth_token)
                        dataStoreManager.save(key = "first_name", value = first_name ?: "")
                        dataStoreManager.save(key = "last_name", value = last_name ?: "")
                    }

                    val intent=Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }

        }


    }
}