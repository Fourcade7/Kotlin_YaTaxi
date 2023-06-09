package com.pr7.yataxi.data.model.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize



data class RegisterResponse(
    val first_name: String = "",
    val id: Int = 0,
    val last_name: String = "",
    val phone_number: Any?=null,

)
//@Parcelize
//data class RegisterResponseError(
//    val phone_number: List<String> = emptyList()
//):Parcelable


//{ "id": 2341, "person": "Bob" }
//... and ...
//
//{ "id": 5382, "user": "Mary" }
//@SerializedName(value="phone_number", alternate=["person", "user"])


