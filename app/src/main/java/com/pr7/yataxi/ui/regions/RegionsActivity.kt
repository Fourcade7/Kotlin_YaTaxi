package com.pr7.yataxi.ui.regions

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.pr7.yataxi.data.pref.DataStoreManager
import com.pr7.yataxi.databinding.ActivityRegionsBinding
import com.pr7.yataxi.ui.direcions.DirecionsActivity
import com.pr7.yataxi.utilits.alertdialogprogressbar
import com.pr7.yataxi.utilits.showlogd
import kotlinx.coroutines.launch

class RegionsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegionsBinding
    val regionsModel:RegionsModel by viewModels()
    lateinit var dataStoreManager: DataStoreManager
    var token=MutableLiveData<String>("")
    val mmap= mutableMapOf<String,Int>()

    var directionfrom: String? =null
    var directionto:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FEFFFF")
        dataStoreManager = DataStoreManager(this@RegionsActivity)
        var mydialog=alertdialogprogressbar(Dialog(this))

        getToken()

        token.observe(this@RegionsActivity){
            if (it!=""){
                regionsModel.getAllRegions(it)
            }
        }

        regionsModel.succesregion.observe(this@RegionsActivity){
            if (it){
                mydialog.show()
            }else{
                mydialog.dismiss()
            }
        }
        regionsModel.regionResponseLiveData.observe(this@RegionsActivity){
            val viloyatarraylist=ArrayList<String>()
            viloyatarraylist.add("Viloyatni tanla tez")
            for (i in 0 until it.results.size){
                viloyatarraylist.add(it.results.get(i).name)
                for (j in 0 until it.results.get(i).districts.size){
                   // showlogd(it.results.get(i).districts.get(j).name)
                }
            }
            val arrayAdapter=ArrayAdapter(this@RegionsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,viloyatarraylist)
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
                            val arrayAdaptert=ArrayAdapter(this@RegionsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choosetumanarraylist)
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
                            val arrayAdaptert=ArrayAdapter(this@RegionsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tumanarraylist)
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
                            val arrayAdaptert=ArrayAdapter(this@RegionsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,choosetumanarraylist)
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
                            val arrayAdaptert=ArrayAdapter(this@RegionsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tumanarraylist)
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
            val intent=Intent(this@RegionsActivity,DirecionsActivity::class.java)
            val from=mmap.get(directionfrom)
            val to=mmap.get(directionto)

            if (from!=null && to!=null){
                intent.apply {
                    putExtra("from",from)
                    putExtra("to",to)
                    putExtra("token",token.value)
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