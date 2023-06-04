package com.pr7.yataxi.data.model.body

data class RegisterBody(
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_number: String
)