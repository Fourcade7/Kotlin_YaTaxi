package com.pr7.yataxi.utilits

import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pr7.yataxi.R


fun AppCompatActivity.showtoast(text:String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}
fun AppCompatActivity.showlogd(text:String){
    Log.d("PR77777", "logcat message: $text")
}

fun AppCompatActivity.alertdialogprogressbar(dialog: Dialog):Dialog{
    val vieww=LayoutInflater.from(this).inflate(R.layout.progresssslayout,null)
    val alertDialog=AlertDialog.Builder(this)
    alertDialog.setView(vieww)
    alertDialog.setTitle("Kuting")

    return alertDialog.create()

}



fun main(){
    val box:Box<Int> =Box<Int>(4)
    println(box.value)

    val bmw=Car.BMW("x6")
    println(bmw.fullname)

    val response= getData()
    when(response){
        is Data.Succes->{
            println(response.data)
            println(response.fulldata)
        }
        is Data.Error->{
            println(response.error)
            println(response.fulldata)
        }

    }

}

class Box<T>(t: T) {
    var value = t
}

sealed class Car(val fullname:String){
    class BMW(val name:String):Car(name)
    class Audi(val name:String):Car(name)

}

fun getData():Data<Any>{
    return Data.Succes(200)
}

sealed class Data<T>(val fulldata: T){
    data class Succes<T>(val data:T):Data<T>(data)
    data class Error<T>(val error:T):Data<T>(error)
}


