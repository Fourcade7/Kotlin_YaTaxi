package com.pr7.yataxi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.RecyclerView

import com.pr7.yataxi.databinding.RecyclerviewCarItemBinding

class CarAdapter constructor(
    val context: Context,
    val array: Array<Int>
):RecyclerView.Adapter<CarAdapter.MyViewHolder>() {

  //val mainActivity:MainActivity=context as MainActivity

    var firstpositon=true



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=RecyclerviewCarItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {





            imageviewcar.setImageResource(array.get(position))
//            linearlayout1.setOnClickListener {
//                mainActivity.scroltoPostion(position)
//            }


        }
    }
    override fun getItemCount(): Int =array.size



    class MyViewHolder(val binding: RecyclerviewCarItemBinding):RecyclerView.ViewHolder(binding.root)
}