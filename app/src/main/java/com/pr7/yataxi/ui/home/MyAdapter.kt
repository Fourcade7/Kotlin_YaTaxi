package com.pr7.yataxi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.RecyclerView

import com.pr7.yataxi.databinding.RecyclerviewitemBinding

class MyAdapter constructor(
    val context: Context,
    val array: Array<Int>,
    val arrayname: Array<String>
):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

  //val mainActivity:MainActivity=context as MainActivity

    var firstpositon=true



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=RecyclerviewitemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {





            imageview1.setImageResource(array.get(position))
            textviewregionsname.text=arrayname.get(position)
//            linearlayout1.setOnClickListener {
//                mainActivity.scroltoPostion(position)
//            }


        }
    }
    override fun getItemCount(): Int =array.size



    class MyViewHolder(val binding: RecyclerviewitemBinding):RecyclerView.ViewHolder(binding.root)
}