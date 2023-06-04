package com.pr7.yataxi.data.model.response

data class LoginResponse(
    val auth_token: String="",
    val non_field_errors: List<String> = listOf("")
)


