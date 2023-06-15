package com.pr7.yataxi.ui.direcions

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pr7.yataxi.R
import com.pr7.yataxi.data.model.response.Result
import com.pr7.yataxi.databinding.DirectionsLayoutItemBinding
import com.pr7.yataxi.ui.driver.DriverActivity


class DirectionsAdapter constructor(
    val context: Context,
    val results:List<Result>
):RecyclerView.Adapter<DirectionsAdapter.DriverViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionsAdapter.DriverViewHolder {
       val view=DirectionsLayoutItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return DriverViewHolder(view)
    }



    override fun onBindViewHolder(holder: DirectionsAdapter.DriverViewHolder, position: Int) {

        holder.binding.apply {
            textviewdriverprice.text="${results.get(position).price} UZS"
            //textviewdriverraiting.text=results.get(position).rating.toString()
            textviewdrivernamelastname.text="${results.get(position).owner.first_name} ${results.get(position).owner.last_name}"
            textviewdriverdirections.text="${results.get(position).direction_from.name} - ${results.get(position).direction_to.name} "
            imageviewavatardriver.setImageResource(when(results.get(position).owner.car.name){
                "Malibu"-> R.drawable.malibu
                "Spark"-> R.drawable.spark_green
                "Damas"-> R.drawable.damas
                "Nexia"-> R.drawable.nexia
                "Matiz"-> R.drawable.matiz
                "Gentra"-> R.drawable.gentra
                "Captiva"-> R.drawable.captiva
                "Tracker"-> R.drawable.tracker
                "Cobalt"-> R.drawable.cobalt_red
                "Onix"-> R.drawable.onix
                "Lacetti"-> R.drawable.lacetti
                "Equonix"-> R.drawable.equinox
                "Tahoe"-> R.drawable.tahoe
                "Traverse"-> R.drawable.traverse
                else ->{R.drawable.bus}

            })
            relativelaydirections.setOnClickListener {
                val intent=Intent(context,DriverActivity::class.java)
                intent.putExtra("name","${results.get(position).owner.first_name} ${results.get(position).owner.last_name}")
                intent.putExtra("directions","${results.get(position).direction_from.name} - ${results.get(position).direction_to.name}")
                intent.putExtra("car","${results.get(position).owner.car.name}")
                intent.putExtra("phone","${results.get(position).owner.phone_number}")
                context.startActivity(intent)
            }
        }

    }
    override fun getItemCount(): Int =results.size

    class DriverViewHolder(val binding:DirectionsLayoutItemBinding):RecyclerView.ViewHolder(binding.root)

}