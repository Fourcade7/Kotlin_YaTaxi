package com.pr7.yataxi.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.pr7.yataxi.R
import com.pr7.yataxi.data.pref.DataStoreManager
import com.pr7.yataxi.databinding.ActivityHomeBinding
import com.pr7.yataxi.ui.driver.DriverActivity
import com.pr7.yataxi.utilits.alertdialogprogressbar
import com.pr7.yataxi.utilits.showlogd
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val homeViewModel:HomeViewModel by viewModels()
    lateinit var dataStoreManager: DataStoreManager
    var token=MutableLiveData<String>("")
    val mmap= mutableMapOf<String,Int>()

    var directionfrom: String? =null
    var directionto:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEFFFF")
        dataStoreManager = DataStoreManager(this@HomeActivity)
        var mydialog=alertdialogprogressbar(Dialog(this))

        getToken()

        token.observe(this@HomeActivity){
            if (it!=""){
                homeViewModel.getAllRegions(it)
            }
        }

        homeViewModel.succesregion.observe(this@HomeActivity){
            if (it){
                mydialog.show()
            }else{
                mydialog.dismiss()
            }
        }
        homeViewModel.regionResponseLiveData.observe(this@HomeActivity){
            val viloyatarraylist=ArrayList<String>()
            viloyatarraylist.add("Viloyatni tanla tez")
            for (i in 0 until it.results.size){
                viloyatarraylist.add(it.results.get(i).name)
                for (j in 0 until it.results.get(i).districts.size){
                   // showlogd(it.results.get(i).districts.get(j).name)
                }
            }
            val arrayAdapter=ArrayAdapter(this@HomeActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,viloyatarraylist)
            binding.apply {
                spinnerviloyatfrom.adapter=arrayAdapter

                spinnerviloyatto.adapter=arrayAdapter


                spinnerviloyatfrom.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        //showlogd(it.results.get(position).name)
                        //showlogd(id.toString())
                        val tumanarraylist=ArrayList<String>()
                        val choosetumanarraylist=ArrayList<String>()
                        choosetumanarraylist.add("Kuda ketasan ?")
                        if (position==0){
                            val arrayAdaptert=ArrayAdapter(this@HomeActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choosetumanarraylist)
                            spinnertumanfrom.adapter=arrayAdaptert
                        }else{
                            for (j in 0 until it.results.get(position-1).districts.size){
                                //showlogd(it.results.get(position-1).districts.get(j).name)
                                tumanarraylist.add(it.results.get(position-1).districts.get(j).name)
                                mmap.put(it.results.get(position-1).districts.get(j).name,it.results.get(position-1).districts.get(j).id)

                            }
                            mmap.forEach { t, u ->
                               // showlogd("$t->$u")
                            }
                            val arrayAdaptert=ArrayAdapter(this@HomeActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tumanarraylist)
                            spinnertumanfrom.adapter=arrayAdaptert
                        }



                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }
                spinnerviloyatto.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        //showlogd(it.results.get(position).name)
                        //showlogd(id.toString())
                        val tumanarraylist=ArrayList<String>()
                        val choosetumanarraylist=ArrayList<String>()
                        choosetumanarraylist.add("Kuda ketasan ?")
                        if (position==0){
                            val arrayAdaptert=ArrayAdapter(this@HomeActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choosetumanarraylist)
                            spinnertumanto.adapter=arrayAdaptert
                        }else{
                            
                            for (j in 0 until it.results.get(position-1).districts.size){
                                //showlogd(it.results.get(position-1).districts.get(j).name)
                                tumanarraylist.add(it.results.get(position-1).districts.get(j).name)
                                mmap.put(it.results.get(position-1).districts.get(j).name,it.results.get(position-1).districts.get(j).id)
                            }
                            mmap.forEach { t, u ->
                                //showlogd("$t->$u")
                            }
                            val arrayAdaptert=ArrayAdapter(this@HomeActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tumanarraylist)
                            spinnertumanto.adapter=arrayAdaptert
                        }



                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            }


        }

        binding.apply {
            spinnertumanfrom.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //showlogd(spinnertumanfrom.selectedItem.toString())
                    directionfrom=spinnertumanfrom.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            spinnertumanto.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //showlogd(spinnertumanto.selectedItem.toString())
                    directionto=spinnertumanto.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        }

        binding.buttonconfirm.setOnClickListener {
            val intent=Intent(this@HomeActivity,DriverActivity::class.java)
            val from=mmap.get(directionfrom)
            val to=mmap.get(directionto)

            if (from!=null && to!=null){
                intent.apply {
                    putExtra("from",from)
                    putExtra("to",to)
                }
                startActivity(intent)
            }

            showlogd(mmap.get(directionfrom).toString())
            showlogd(mmap.get(directionto).toString())
        }








    }

    fun getToken(){

        lifecycleScope.launch {
            //delay(1000)
            dataStoreManager.load("token").collect{

               if (it!=""){
                   showlogd(it)
                   token.value=it
               }
            }
        }

    }


}